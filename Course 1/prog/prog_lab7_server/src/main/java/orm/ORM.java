package orm;

import annotations.Id;
import annotations.Table;
import annotations.constraints.*;
import annotations.relationshipType.Element;
import annotations.relationshipType.OneToMany;
import exceptions.ConvertInstructionException;
import model.Color;
import model.Country;
import model.FormOfEducation;
import model.Semester;
import utils.CheckedFunction;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

public class ORM<T> implements ORMInterface<T> {
    private final DataSource dataSource;
    private final Class<? super T> clazz;

    // Map with instance of orm for classes
    private static final Map<Class<?>, String> typeConverter = new HashMap<>();
    private static final Map<Class<?>, CheckedFunction<String, Object, ConvertInstructionException>> toObjectConvertInstructions = new HashMap<>();
    private final Map<Class<?>, ORM<?>> ormInstancesForClasses = new HashMap<>();

    // Strings with requests to db
    private String createTableRequestToDB;
    private String insertRequestToDb;

    // Lock
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public ORM(DataSource dataSource, Class<? super T> clazz) {
        this.dataSource = dataSource;
        this.clazz = clazz;
    }

    @Override
    public boolean save(T object) throws SQLException {
        try {
            readWriteLock.writeLock().lock();
            insert(object, null);
        } catch (IllegalAccessException ignored) {
            System.out.println(ignored.getMessage());
        }
        finally {
            readWriteLock.writeLock().unlock();
            return true;
        }
    }
    @Override
    public boolean save(T object, Integer id) throws SQLException {
        try {
            //readWriteLock.writeLock().lock();
            System.out.println(888);

            insert(object, id);
        } catch (IllegalAccessException ignored) { }
        finally {
            //readWriteLock.writeLock().unlock();
            return true;
        }
    }

    @Override
    public List<T> getObjects() throws SQLException {
        try {
            readWriteLock.readLock().lock();
            return (List<T>) getObjWithFetch(new String[]{});
        } catch (InstantiationException | IllegalAccessException | ConvertInstructionException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
            return new ArrayList<>();
        }
    }

    @Override
    public List<T> getObjects(String... fetch) throws SQLException {
        try {
            readWriteLock.readLock().lock();
            return (List<T>) getObjWithFetch(fetch);
        } catch (InstantiationException | IllegalAccessException | ConvertInstructionException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean remove(T object) throws SQLException {
        try {
            readWriteLock.writeLock().lock();
            deleteFromDB(object);
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().lock();
            return true;
        }
    }

    @Override
    public boolean update(T object) throws SQLException {
        return true;
    }

    public void prepare() {
        setCreateTableRequestToDB(null);
        setInsertRequestToDb(null);
    }

    public void createTables() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableRequestToDB);
        }
        for (ORM<?> orm:
             ormInstancesForClasses.values()) {
            orm.createTables();
        }
    }

    // Private Methods
    private void deleteFromDB(Object o) throws SQLException, IllegalAccessException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            for (Field field:
                    clazz.getDeclaredFields()) {
                if (Objects.nonNull(field.getAnnotation(Id.class))) {
                    field.setAccessible(true);
                    statement.execute("DELETE FROM " + getTableName() + " WHERE id = " + field.get(o));
                    field.setAccessible(false);
                }
            }
        }
    }

    private ArrayList<Object> getObjWithFetch(String... fetch) throws SQLException, InstantiationException, IllegalAccessException, ConvertInstructionException {
        ArrayList<Object> objects = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "
                    + getTableName()
                    + (fetch.length > 0 ? " WHERE " : " ")
                    + String.join(" AND ", fetch));

            while (resultSet.next()) {

                Object object = clazz.newInstance();

                for (Field field :
                        clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    Element elementAnnotation = field.getAnnotation(Element.class);
                    OneToMany toManyElement = field.getAnnotation(OneToMany.class);
                    Id idAnnotation = field.getAnnotation(Id.class);
                    if (Objects.nonNull(elementAnnotation)) {
                        if (typeConverter.containsKey(field.getType())) {
                            String value = resultSet.getString(elementAnnotation.name().equals("") ? field.getName() : elementAnnotation.name());
                            field.set(object, toObjectConvertInstructions.get(field.getType()).apply(value));
                        } else if (String.class.equals(field.getType())) {
                            String value = resultSet.getString(elementAnnotation.name().equals("") ? field.getName() : elementAnnotation.name());
                            field.set(object, value);
                        } else {
                            ORM<?> orm = getORMForClass(field.getType());
                            String id = resultSet.getString("id");
                            field.set(object, orm.getObjWithFetch(getTableName() + "_id = " + id).get(0));
                        }
                    }
                    if (Objects.nonNull(toManyElement)) {
                        setArrayToField(resultSet, object, field);
                    }
                    if (Objects.nonNull(idAnnotation)) {
                        field.set(object, resultSet.getInt("id"));
                    }
                }
                objects.add(object);
            }
        }
        return objects;
    }

    private void setArrayToField(ResultSet resultSet, Object object, Field field) throws SQLException, InstantiationException, IllegalAccessException, ConvertInstructionException {
        String id = resultSet.getString("id");
        ParameterizedType paraType = (ParameterizedType) field.getGenericType();
        Class<?> aClass = (Class<?>) paraType.getActualTypeArguments()[0];
        ORM<?> orm = getORMForClass(aClass);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(orm.getObjWithFetch(getTableName() + "_id = " + id));
        field.set(object, arrayList);
    }

    private void insert(Object object, Integer identifier) throws SQLException, IllegalAccessException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRequestToDb)) {
            int index = 1;
            Field idField = null;
            if (Objects.nonNull(identifier)) {
                preparedStatement.setInt(index++, identifier);
            }
            LinkedList<Object> nonPrimaryElements = new LinkedList<>();
            for (Field field:
                    clazz.getDeclaredFields()) {

                field.setAccessible(true);

                Element elementAnnotation = field.getAnnotation(Element.class);
                OneToMany toManyElement = field.getAnnotation(OneToMany.class);
                Id idAnnotation = field.getAnnotation(Id.class);

                if (Objects.nonNull(elementAnnotation)) {
                    if (typeConverter.containsKey(field.getType()) || String.class.equals(field.getType())) {
                        preparedStatement.setObject(index++, field.get(object));
                    }
                    else {
                        nonPrimaryElements.add(field.get(object));
                    }
                }

                if (Objects.nonNull(idAnnotation)) idField = field;

                if (Objects.nonNull(toManyElement)) {
                    ArrayList arr = (ArrayList) field.get(object);
                    arr.forEach(o -> nonPrimaryElements.add(o));
                }

                field.setAccessible(false);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            if (Objects.nonNull(idField)) {
                idField.setAccessible(true);
                idField.set(object, resultSet.getInt("id"));
                System.out.println(resultSet.getInt("id"));
                System.out.println(idField.getName());
                idField.setAccessible(false);
            }

            for (Object element:
                    nonPrimaryElements) {
                getORMForClass(element.getClass()).insert(element, resultSet.getInt("id"));
            }
        }
    }


    // Methods for creating requests
    private void setInsertRequestToDb(String ownerTable) {
        StringBuilder insertRequest = new StringBuilder("INSERT INTO " + getTableName() +" (");

        if (Objects.nonNull(ownerTable)) {
            insertRequest
                    .append(ownerTable +"_id")
                    .append(", ");
        }

        int countOfColumns = Objects.nonNull(ownerTable) ?  1 : 0;
        for (Field field:
                clazz.getDeclaredFields()) {
            Optional<Element> elementAnnotation = Optional.ofNullable(field.getAnnotation(Element.class));
            Optional<OneToMany> toManyElementAnnotation = Optional.ofNullable(field.getAnnotation(OneToMany.class));

            if (elementAnnotation.isPresent()) {
                if (typeConverter.containsKey(field.getType()) || String.class.equals(field.getType())) {
                    countOfColumns++;
                    insertRequest
                            .append(field.getName())
                            .append(", ");
                } else {
                    ORM<?> orm = getORMForClass(field.getType());
                    orm.setInsertRequestToDb(getTableName());
                }
            }
            if (toManyElementAnnotation.isPresent()) {
                ParameterizedType paraType = (ParameterizedType) field.getGenericType();
                Class<?> aClass = (Class<?>) paraType.getActualTypeArguments()[0];
                ORM<?> orm = getORMForClass(aClass);
                orm.setInsertRequestToDb(getTableName());
            }
        }
        insertRequest.replace(insertRequest.length() - 2, insertRequest.length() - 1, ")")
                .append(" VALUES ");

        if (countOfColumns != 0) {
            insertRequest.append("(");
            while(countOfColumns-- != 0) {
                insertRequest.append("?, ");
            }
        }

        insertRequest.replace(insertRequest.length() - 2, insertRequest.length() - 1, ") RETURNING id;");
        insertRequestToDb = insertRequest.toString();
        System.out.println(insertRequestToDb);
    }

    private void setCreateTableRequestToDB(String ownerTableName) {
        StringBuilder createRequest = new StringBuilder("CREATE TABLE IF NOT EXISTS " + clazz.getAnnotation(Table.class).value() + "(\n");

        // Add id column
        createRequest.append("\tid SERIAL PRIMARY KEY,\n");

        // Add other columns
        for (Field field :
                clazz.getDeclaredFields()) {

            Optional<Element> oneToOneAnnotation = Optional.ofNullable(field.getDeclaredAnnotation(Element.class));
            Optional<OneToMany> oneToManyAnnotation = Optional.ofNullable(field.getDeclaredAnnotation(OneToMany.class));

            Optional<NoNull> noNullAnnotation = Optional.ofNullable(field.getDeclaredAnnotation(NoNull.class));
            Optional<Unique> uniqueAnnotation = Optional.ofNullable(field.getDeclaredAnnotation(Unique.class));

            Optional<GreaterThan> greaterThanAnnotation = Optional.ofNullable(field.getDeclaredAnnotation(GreaterThan.class));
            Optional<LessThan> lessThanAnnotation = Optional.ofNullable(field.getDeclaredAnnotation(LessThan.class));
            Optional<MaxLength> maxLengthAnnotation = Optional.ofNullable(field.getDeclaredAnnotation(MaxLength.class));
            Optional<MinLength> minLengthAnnotation = Optional.ofNullable(field.getDeclaredAnnotation(MinLength.class));

            if (oneToOneAnnotation.isPresent()) {
                String nameOfFieldInDb = oneToOneAnnotation.get().name().equals("") ? field.getName() : oneToOneAnnotation.get().name();
                if (typeConverter.containsKey(field.getType())) {
                    createRequest
                            .append("\t")
                            .append(nameOfFieldInDb)
                            .append(" ")
                            .append(typeConverter.get(field.getType()))
                            .append(noNullAnnotation.isPresent() ? " NOT NULL" : "")
                            .append(uniqueAnnotation.isPresent() ? " UNIQUE" : "")
                            .append(",\n");
                    createRequest
                            .append(greaterThanAnnotation.map(greaterThan -> "\tCHECK (" + nameOfFieldInDb + " > " + greaterThan.value() + "),\n").orElse(""));
                    createRequest
                            .append(lessThanAnnotation.map(lessThan -> "\tCHECK (" + nameOfFieldInDb + " < " + lessThan.value() + "),\n").orElse(""));
                } else if (Objects.equals(field.getType(), String.class)) {
                    createRequest
                            .append("\t")
                            .append(nameOfFieldInDb)
                            .append(" ")
                            .append(maxLengthAnnotation.map(maxLength -> "VARCHAR(" + maxLength.value() + ")").orElse("TEXT"))
                            .append(noNullAnnotation.isPresent() ? " NOT NULL" : "")
                            .append(uniqueAnnotation.isPresent() ? " UNIQUE" : "")
                            .append(",\n");
                    createRequest
                            .append(minLengthAnnotation.map(minLength -> "\tCHECK (LENGTH (" + nameOfFieldInDb + ") >= " + minLength.value() + "),\n").orElse(""));
                    createRequest
                            .append(maxLengthAnnotation.map(maxLength -> "\tCHECK (LENGTH (" + nameOfFieldInDb + ") <= " + maxLength.value() + "),\n").orElse(""));
                } else {
                    ORM<?> subORM = getORMForClass(field.getType());
                    subORM.setCreateTableRequestToDB(getTableName());
                }
            }
            if (oneToManyAnnotation.isPresent()) {
                ParameterizedType paraType = (ParameterizedType) field.getGenericType();
                Class<?> aClass = (Class<?>) paraType.getActualTypeArguments()[0];
                ORM<?> orm = getORMForClass(aClass);
                orm.setCreateTableRequestToDB(getTableName());
            }
        }
        if (Objects.nonNull(ownerTableName)) {
            createRequest
                    .append("\t")
                    .append(ownerTableName)
                    .append("_id")
                    .append(" INT REFERENCES " )
                    .append(ownerTableName)
                    .append(" (id) ")
                    .append("ON DELETE CASCADE  ");
        }
        createRequest.replace(createRequest.length() - 2, createRequest.length() - 1, ");");
        createTableRequestToDB = createRequest.toString();
        System.out.println(createTableRequestToDB);
    }

    private String getTableName() {
        return clazz.getAnnotation(Table.class).value();
    }

    public <S> ORM<S> getORMForClass(Class<S> clazz) {
        if (ormInstancesForClasses.containsKey(clazz))
            return (ORM<S>) ormInstancesForClasses.get(clazz);
        ORM<S> orm = new ORM<S>(dataSource, clazz);
        ormInstancesForClasses.put(clazz, orm);
        return orm;
    }

    // Static methods

    static {
        addInstructionsForType(Integer.class, getWrappedParseNumFunction(Integer::parseInt), Constants.intDB);
        addInstructionsForType(Long.class, getWrappedParseNumFunction(Long::parseLong), Constants.bigIntDB);
        addInstructionsForType(Boolean.class, getWrappedParseNumFunction(Boolean::parseBoolean), Constants.booleanDB);
        addInstructionsForType(Byte.class, getWrappedParseNumFunction(Byte::parseByte), Constants.smallIntDB);
        addInstructionsForType(Float.class, getWrappedParseNumFunction(Float::parseFloat), Constants.realDB);
        addInstructionsForType(Double.class, getWrappedParseNumFunction(Double::parseDouble), Constants.doubleDB);
        addInstructionsForType(Short.class, getWrappedParseNumFunction(Short::parseShort), Constants.smallIntDB);
        addInstructionsForType(Character.class, string -> string.charAt(0), Constants.charDB);

        addInstructionsForType(int.class, getWrappedParseNumFunction(Integer::parseInt), Constants.intDB);
        addInstructionsForType(long.class, getWrappedParseNumFunction(Long::parseLong), Constants.bigIntDB);
        addInstructionsForType(boolean.class, getWrappedParseNumFunction(Boolean::parseBoolean), Constants.booleanDB);
        addInstructionsForType(byte.class, getWrappedParseNumFunction(Byte::parseByte), Constants.smallIntDB);
        addInstructionsForType(float.class, getWrappedParseNumFunction(Float::parseFloat), Constants.realDB);
        addInstructionsForType(double.class, getWrappedParseNumFunction(Double::parseDouble), Constants.doubleDB);
        addInstructionsForType(short.class, getWrappedParseNumFunction(Short::parseShort), Constants.smallIntDB);
        addInstructionsForType(char.class, string -> string.charAt(0), Constants.charDB);

        addInstructionsForType(Semester.class, s -> Semester.valueOf(s), "TEXT");
        addInstructionsForType(FormOfEducation.class, s -> FormOfEducation.valueOf(s), "TEXT");
        addInstructionsForType(Country.class, s -> Country.valueOf(s), "TEXT");
        addInstructionsForType(Color.class, s -> Color.valueOf(s), "TEXT");
    }

    private static CheckedFunction<String, Object, ConvertInstructionException> getWrappedParseNumFunction(Function<String, Object> parseFunction) {
        return string -> {
            try {
                return parseFunction.apply(string);
            } catch (NumberFormatException e) {
                throw new ConvertInstructionException();
            }
        };
    }

    private static void addInstructionsForType(Class<?> clazz,
                                               CheckedFunction<String, Object, ConvertInstructionException> function,
                                               String dbType) {
        toObjectConvertInstructions.put(clazz, function);
        typeConverter.put(clazz, dbType);
    }

    private static final class Constants {
        static String intDB = "INT";
        static String bigIntDB = "BIGINT";
        static String booleanDB = "BOOLEAN";
        static String smallIntDB = "SMALLINT";
        static String realDB = "REAL";
        static String doubleDB = "REAL";
        static String charDB = "CHARACTER [1]";
    }
}
