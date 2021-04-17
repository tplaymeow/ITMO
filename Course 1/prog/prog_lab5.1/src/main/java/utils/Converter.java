package utils;

import annotations.converterAnnotations.AutomaticGenerated;
import annotations.converterAnnotations.NotWrite;
import exceptions.AnnotationException;
import exceptions.ConvertInstructionException;
import exceptions.EndOfFileException;
import exceptions.ValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс конвертор объекета в массив строк и обратно
 * @param <T> тип объекта
 */
public class Converter<T> {
    private final Class<?> clazz;
    private static final Map<Class<?>, ToObjectInterface> toObjectConvertInstructions = new HashMap<>();
    private static final Map<Class<?>, String> possibleValuesStrings = new HashMap<>();

    private static final String namesOfFieldsSeparator = "/";

    static {
        toObjectConvertInstructions.put(int.class, getWrappedParseNumFunction(Integer::parseInt));
        toObjectConvertInstructions.put(Integer.class, getWrappedParseNumFunction(Integer::parseInt));
        toObjectConvertInstructions.put(long.class, getWrappedParseNumFunction(Long::parseLong));
        toObjectConvertInstructions.put(Long.class, getWrappedParseNumFunction(Long::parseLong));
        toObjectConvertInstructions.put(boolean.class, getWrappedParseNumFunction(Boolean::parseBoolean));
        toObjectConvertInstructions.put(Boolean.class, getWrappedParseNumFunction(Boolean::parseBoolean));
        toObjectConvertInstructions.put(byte.class, getWrappedParseNumFunction(Byte::parseByte));
        toObjectConvertInstructions.put(Byte.class, getWrappedParseNumFunction(Byte::parseByte));
        toObjectConvertInstructions.put(float.class, getWrappedParseNumFunction(Float::parseFloat));
        toObjectConvertInstructions.put(Float.class, getWrappedParseNumFunction(Float::parseFloat));
        toObjectConvertInstructions.put(double.class, getWrappedParseNumFunction(Double::parseDouble));
        toObjectConvertInstructions.put(Double.class, getWrappedParseNumFunction(Double::parseDouble));
        toObjectConvertInstructions.put(short.class, getWrappedParseNumFunction(Short::parseShort));
        toObjectConvertInstructions.put(Short.class, getWrappedParseNumFunction(Short::parseShort));
        toObjectConvertInstructions.put(String.class, string -> string);
    }

    public Converter(Class<? extends T> clazz) {
        this.clazz = clazz;
    }

    private static ToObjectInterface getWrappedParseNumFunction(ParseNumInterface parseFunction) {
        return string -> {
            try {
                return parseFunction.parse(string);
            } catch (NumberFormatException e) {
                throw new ConvertInstructionException();
            }
        };
    }

    /**
     * Добавляет функцию для перевода строки в объект конкретного класса
     * @param clazz класс
     * @param toObjectInterface функция
     */
    public void addInstruction(Class<?> clazz, ToObjectInterface toObjectInterface) {
        toObjectConvertInstructions.put(clazz, toObjectInterface);
    }

    /**
     * Добавляет функцию для Enumа
     * @param enumClass класс Enuma
     */
    public void addInstructionForEnum(Class<? extends Enum> enumClass) {
        toObjectConvertInstructions.put(enumClass, (inputEnum) -> {
            try {
                return Enum.valueOf((Class<Enum>) enumClass, inputEnum);
            } catch (IllegalArgumentException e) {
                throw new ConvertInstructionException();
            }
        });
    }

    /**
     * Добавляет возможные значения для Enuma
     */
    public void addPossibleValuesForEnum(Class<? extends Enum> enumClass) {
        possibleValuesStrings.put(enumClass, String.join(", ", Arrays.toString(enumClass.getEnumConstants())));
    }

    /**
     * Возвращает имена полей у класса
     * @return массив имен полей
     */
    public ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();

        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(NotWrite.class))
                .forEach(field -> {
                    if (toObjectConvertInstructions.containsKey(field.getType())) {
                        names.add(field.getName());
                    } else {
                        Converter<?> subConverter = new Converter<>(field.getType());
                        subConverter.getNames().stream()
                                .map(string -> field.getName() + namesOfFieldsSeparator + string)
                                .forEach(names::add);
                    }
                });
        return names;
    }

    /**
     * Переводит объект в массив строк
     * @param object объект
     * @return массив строк - значений его полей
     */
    public ArrayList<String> objectToData(T object) {
        ArrayList<String> data = new ArrayList<>();

        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(NotWrite.class))
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        if (toObjectConvertInstructions.containsKey(field.getType())) {
                            data.add(field.get(object).toString());
                        } else {
                            Converter<Object> subConverter = new Converter<>(field.getType());
                            data.addAll(subConverter.objectToData(field.get(object)));
                        }
                    } catch (IllegalAccessException | IllegalArgumentException ignored) { }
                });
        return data;
    }

    /**
     * Получает объект из его данных
     * @param names имена полей
     * @param data значения полей
     * @param withValidation использовать ли валидацию
     * @return объект
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws AnnotationException
     * @throws ValidationException
     * @throws ConvertInstructionException
     */
    public T dataToObject(ArrayList<String> names, ArrayList<String> data, boolean withValidation)
            throws InstantiationException, IllegalAccessException, NoSuchFieldException, AnnotationException, ValidationException, ConvertInstructionException {
        Object object = clazz.newInstance();
        // Sort keys with data
        ArrayList<String> sortedNames = new ArrayList<>();
        ArrayList<String> sortedData = new ArrayList<>();
        Map<String, String> treeMap = new TreeMap<String, String>();
        try {
            for (int i = 0; i < names.size(); i++) treeMap.put(names.get(i), data.get(i));
            treeMap.forEach((name, value) -> {
                sortedNames.add(name);
                sortedData.add(value);
            });
        } catch (IndexOutOfBoundsException e) {
            throw new ValidationException();
        }

        for (int i = 0; i < data.size(); i++) {
            String name = sortedNames.get(i);
            String value = sortedData.get(i);

            if (name.contains(namesOfFieldsSeparator)) {
                name = name.split(namesOfFieldsSeparator, 2)[0];
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                ArrayList<String> sub_names = new ArrayList<>();
                ArrayList<String> sub_values = new ArrayList<>();

                for (int j = i; j < data.size(); j++) {
                    if (sortedNames.get(i).startsWith(name)) {
                        sub_names.add(sortedNames.get(i).split(namesOfFieldsSeparator, 2)[1]);
                        sub_values.add(sortedData.get(i));
                        i++;
                    }
                }
                i--;
                Converter<Object> subConverter = new Converter<>(field.getType());
                field.set(object, subConverter.dataToObject(sub_names, sub_values, withValidation));

            } else {
                try {
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);
                    Object objValue = value.equals("") ? null : toObjectConvertInstructions.get(field.getType()).toObject(value);
                    if (withValidation && !Validator.validateField(field, objValue)) throw new ValidationException();
                    field.set(object, objValue);
                } catch (SecurityException ignored) { }
            }
        }
        return (T) object;
    }

    /**
     * Получает объект из потока ввода
     * @param inputStream поток ввода
     * @param printer функция для вывода сообщений
     * @return объект
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws EndOfFileException
     * @throws IOException
     * @throws AnnotationException
     */
    public T inputStreamToObject(InputStream inputStream, Printer printer)
            throws IllegalAccessException, InstantiationException, EndOfFileException, IOException, AnnotationException {
        Object object = clazz.newInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(NotWrite.class) && !field.isAnnotationPresent(AutomaticGenerated.class)) {
                field.setAccessible(true);
                if (toObjectConvertInstructions.containsKey(field.getType())) {
                    while (true) {
                        printer.print("Введите значение для поля " + field.getName());
                        if (possibleValuesStrings.containsKey(field.getType()))
                            printer.print("Возможные значения: " + possibleValuesStrings.get(field.getType()));
                        if (Objects.isNull(line = reader.readLine())) throw new EndOfFileException();

                        try {
                            Object objValue = line.equals("") ? null : toObjectConvertInstructions.get(field.getType()).toObject(line);
                            if (Validator.validateField(field, objValue)) {
                                field.set(object, objValue);
                                break;
                            }
                            else printer.print("Значение не коректно. Попробуйте еще раз");
                        } catch (ConvertInstructionException | IllegalArgumentException e) {
                            printer.print("Не верный формат");
                            printer.print(e.getMessage());
                        }
                    }
                } else {
                    printer.print("Введите значения для полей " + field.getName() + ":");
                    Converter<Object> subConverter = new Converter<>(field.getType());
                    field.set(object, subConverter.inputStreamToObject(inputStream, printer));
                }
            }
        }
        return (T) object;
    }

    /**
     * Возвращает коллекцию из данных
     * @param arrayOfDataWithHeader массив массивов со значениями полей и именами полей
     * @param withValidation использовать ли валидацию
     * @return коллекция объектов
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws AnnotationException
     */
    public ArrayList<T> toCollectionOfObjects(ArrayList<ArrayList<String>> arrayOfDataWithHeader, boolean withValidation)
            throws IllegalAccessException, NoSuchFieldException, InstantiationException, AnnotationException {
        ArrayList<T> collection = new ArrayList<>();
        ArrayList<String> header = arrayOfDataWithHeader.get(0);
        arrayOfDataWithHeader.remove(0);
        for (ArrayList<String> data : arrayOfDataWithHeader) {
            try {
                collection.add(dataToObject(header, data, withValidation));
            } catch (ValidationException | ConvertInstructionException e) { /* Элемент не будет добавлен в коллекцию, если не прошел валидацию  */ }
        }
        return collection;
    }
}
