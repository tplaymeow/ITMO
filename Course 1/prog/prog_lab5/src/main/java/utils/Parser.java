package utils;

import model.StudyGroup;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Parser <T> {
    private final static ArrayList<Class> wrapperClasses = new ArrayList<>(Arrays.asList(new Class[]{
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class,
            Boolean.class,
            Character.class
    }));
    private final Class clazz;

    public Parser(Class clazz) {
        this.clazz = clazz;
    }

    public ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Field field :
                clazz.getDeclaredFields()) {
            if (field.getType().isPrimitive()
                    || wrapperClasses.contains(field.getType())
                    || field.getType().equals(String.class)
                    || field.getType().isEnum()
                    || field.getType().equals(LocalDateTime.class)) {
                names.add(field.getName());
            } else {
                Parser<?> sub_parser = new Parser<>(field.getType());
                sub_parser.getNames().forEach(name -> names.add(field.getName() + "/" + name));
            }
        }
        return names;
    }

    // TODO: разобраться с эксепшенами
    public ArrayList<String> objectToData(Object object) throws IllegalAccessException {
        if (clazz.isInstance(object)) {
            ArrayList<String> values = new ArrayList<>();
            for (Field field :
                    clazz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType().isPrimitive()
                        || wrapperClasses.contains(field.getType())
                        || field.getType().equals(String.class)
                        || field.getType().isEnum()
                        || field.getType().equals(LocalDateTime.class)) {
                    values.add(field.get(object).toString());
                } else {
                    Parser<?> sub_parser = new Parser<>(field.getType());
                    values.addAll(sub_parser.objectToData(field.get(object)));
                }
            }
            return values;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Object objectFromData(ArrayList<String> names, ArrayList<String> data) throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Object object = clazz.getConstructor().newInstance();

        for (int i = 0; i < data.size(); i++) {
            String name = names.get(i);
            String value = data.get(i);

            if (name.contains("/")) {
                name = name.split("/", 2)[0];
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                ArrayList<String> sub_names = new ArrayList<>();
                ArrayList<String> sub_values = new ArrayList<>();

                for (int j = i; j < data.size(); j++) {
                    if (names.get(i).startsWith(name)) {
                        sub_names.add(names.get(i).split("/", 2)[1]);
                        sub_values.add(data.get(i));
                    }
                }

                Parser<?> sub_parser = new Parser<>(field.getType());
                field.set(object, sub_parser.objectFromData(sub_names, sub_values));

            } else {
                //тут швыряет эксепшон
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);

                if (field.getType().equals(byte.class) || field.getType().equals(Byte.class)) {
                    field.set(object, Byte.parseByte(value));
                } else if (field.getType().equals(short.class) || field.getType().equals(Short.class)) {
                    field.set(object, Short.parseShort(value));
                } else if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                    field.set(object, Integer.parseInt(value));
                } else if (field.getType().equals(long.class) || field.getType().equals(Long.class)) {
                    field.set(object, Long.parseLong(value));
                } else if (field.getType().equals(float.class) || field.getType().equals(Float.class)) {
                    field.set(object, Float.parseFloat(value));
                } else if (field.getType().equals(double.class) || field.getType().equals(Double.class)) {
                    field.set(object, Double.parseDouble(value));
                } else if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
                    field.set(object, Boolean.parseBoolean(value));
                } else if (field.getType().equals(char.class) || field.getType().equals(Character.class)) {
                    field.set(object, value.charAt(0));
                } else if (field.getType().equals(String.class)) {
                    field.set(object, value);
                } else if (field.getType().isEnum()) {
                    field.set(object, Enum.valueOf((Class<Enum>) field.getType(), value));
                } else if (field.getType().equals(LocalDateTime.class)) {
                    field.set(object, LocalDateTime.parse(value));
                }
            }
        }

        return object;
    }

    public ArrayList<ArrayList<String>> collectionToData(LinkedList<StudyGroup> objects) throws IllegalAccessException {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        for (Object object:
             objects) {
            data.add(objectToData(object));
        }

        return data;
    }

    public ArrayList<T> collectionFromData(ArrayList<ArrayList<String>> data) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        ArrayList<T> collection = new ArrayList<>();
        for (ArrayList<String> objData:
             data) {
            collection.add((T) objectFromData(getNames(), objData));
        }

        return collection;
    }

    public static ArrayList<Class> getWrapperClasses() {
        return wrapperClasses;
    }
}
