package utils;

import annotations.validatorAnnotations.*;
import exceptions.AnnotationException;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Класс - вадидатор. Работает с аннотациями
 */
public class Validator {
    /**
     * Метод используемый для валидации поля
     * @param field валидируемое поле
     * @param value значение поля
     * @return true, если поле прошло валидацию, false, если нет
     * @throws AnnotationException
     */
    public static boolean validateField(Field field, Object value) throws AnnotationException {
        try {
            Between annotation = Objects.requireNonNull(field.getDeclaredAnnotation(Between.class));
            if (!Number.class.isAssignableFrom(field.getType()) && !field.getType().isPrimitive()) throw new AnnotationException(field.getName(), Between.class.getName());
            if (objToNumber(value).doubleValue() < annotation.from()) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        try {
            GreaterThan annotation = Objects.requireNonNull(field.getDeclaredAnnotation(GreaterThan.class));
            if (!Number.class.isAssignableFrom(field.getType()) && !field.getType().isPrimitive()) throw new AnnotationException(field.getName(), GreaterThan.class.getName());
            if (objToNumber(value).doubleValue() < annotation.num()) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        try {
            LongerThan annotation = Objects.requireNonNull(field.getDeclaredAnnotation(LongerThan.class));
            if (!field.getType().equals(String.class)) throw new AnnotationException(field.getName(), LongerThan.class.getName());
            if (value.toString().length() < annotation.length()) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        try {
            ShorterThan annotation = Objects.requireNonNull(field.getDeclaredAnnotation(ShorterThan.class));
            if (!field.getType().equals(String.class)) throw new AnnotationException(field.getName(), ShorterThan.class.getName());
            if (value.toString().length() > annotation.length()) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        try {
            NotEqualString annotation = Objects.requireNonNull(field.getDeclaredAnnotation(NotEqualString.class));
            if (!field.getType().equals(String.class)) throw new AnnotationException(field.getName(), NotEqualString.class.getName());
            if (value.toString().equals(annotation.string())) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        try {
            NotNull annotation = Objects.requireNonNull(field.getDeclaredAnnotation(NotNull.class));
            if (value == null) return false;
        } catch (NullPointerException ignored) { /* У поля нет этой аннотации */ }

        return true;
    }

    /**
     * Вспомогательная функция для конвериации значения в Number
     * @param object объект
     * @return объект класса {@link Number}
     */
    private static Number objToNumber(Object object) {
        if (object.getClass().equals(Byte.class)) {
            return (Byte) object;
        } else if (object.getClass().equals(Short.class)) {
            return (Short) object;
        } else if (object.getClass().equals(Integer.class)) {
            return (Integer) object;
        } else if (object.getClass().equals(Long.class)) {
            return (Long) object;
        } else if (object.getClass().equals(Float.class)) {
            return (Float) object;
        } else if (object.getClass().equals(Double.class)) {
            return (Double) object;
        }
        throw new IllegalArgumentException();
    }
}
