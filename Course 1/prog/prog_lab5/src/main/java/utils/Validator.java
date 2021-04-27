package utils;

import annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Validator {
    private final Class clazz;

    public Validator(Class clazz) {
        this.clazz = clazz;
    }

    public static boolean validateField(Field field, Object value) {
        try {
            Between annotation = field.getDeclaredAnnotation(Between.class);
            if (objToNumber(value, field.getType()).doubleValue() < annotation.from() || objToNumber(value, field.getType()).doubleValue() > annotation.to())
                return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        try {
            GreaterThan annotation = field.getDeclaredAnnotation(GreaterThan.class);
            if (objToNumber(value, field.getType()).doubleValue() < annotation.num()) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        try {
            LongerThan annotation = field.getDeclaredAnnotation(LongerThan.class);
            if (value.toString().length() < annotation.length()) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        try {
            ShorterThan annotation = field.getDeclaredAnnotation(ShorterThan.class);
            if (value.toString().length() > annotation.length()) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        try {
            NotEqualString annotation = field.getDeclaredAnnotation(NotEqualString.class);
            if (value.toString().equals(annotation.string())) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        try {
            NotNull annotation = field.getDeclaredAnnotation(NotNull.class);
            if (value == null) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        return true;
    }

    private static Number objToNumber(Object object, Class type) {
        if (type.equals(byte.class) || type.equals(Byte.class)) {
            return (Byte) object;
        } else if (type.equals(short.class) || type.equals(Short.class)) {
            return (Short) object;
        } else if (type.equals(int.class) || type.equals(Integer.class)) {
            return (Integer) object;
        } else if (type.equals(long.class) || type.equals(Long.class)) {
            return (Long) object;
        } else if (type.equals(float.class) || type.equals(Float.class)) {
            return (Float) object;
        } else if (type.equals(double.class) || type.equals(Double.class)) {
            return (Double) object;
        }

        return null; //TODO:исправить
    }

    public boolean validate(Object object) throws IllegalAccessException {
        if (clazz.isInstance(object)) {
            for (Field field :
                    clazz.getDeclaredFields()) {
                field.setAccessible(true);
                List<Annotation> annotations = Arrays.asList(field.getDeclaredAnnotations());

                if (!(field.getType().isPrimitive()
                        || Parser.getWrapperClasses().contains(field.getType())
                        || field.getType().equals(String.class)
                        || field.getType().isEnum())) {
                    Validator sub_validator = new Validator(field.getType());
                    if (!sub_validator.validate(field.get(object))) {
                        return false;
                    }
                }

                try {
                    Between annotation = field.getDeclaredAnnotation(Between.class);
                    if (objToNumber(field.get(object), field.getType()).doubleValue() < annotation.from() || objToNumber(field.get(object), field.getType()).doubleValue() > annotation.to())
                        return false;
                } catch (NullPointerException | IllegalAccessException ignored) { /* У поля нет этой аннотации */ }

                try {
                    GreaterThan annotation = field.getDeclaredAnnotation(GreaterThan.class);
                    if (objToNumber(field.get(object), field.getType()).doubleValue() < annotation.num()) return false;
                } catch (NullPointerException | IllegalAccessException ignored) { /* У поля нет этой аннотации */ }

                try {
                    LongerThan annotation = field.getDeclaredAnnotation(LongerThan.class);
                    if (field.get(object).toString().length() < annotation.length()) return false;
                } catch (NullPointerException | IllegalAccessException ignored) { /* У поля нет этой аннотации */ }

                try {
                    ShorterThan annotation = field.getDeclaredAnnotation(ShorterThan.class);
                    if (field.get(object).toString().length() > annotation.length()) return false;
                } catch (NullPointerException | IllegalAccessException ignored) { /* У поля нет этой аннотации */ }

                try {
                    NotEqualString annotation = field.getDeclaredAnnotation(NotEqualString.class);
                    if (field.get(object).toString().equals(annotation.string())) return false;
                } catch (NullPointerException | IllegalAccessException ignored) { /* У поля нет этой аннотации */ }

                try {
                    NotNull annotation = field.getDeclaredAnnotation(NotNull.class);
                    if (field.get(object) == null) return false;
                } catch (NullPointerException | IllegalAccessException ignored) { /* У поля нет этой аннотации */ }

            }
        }
        return true;
    }


}
