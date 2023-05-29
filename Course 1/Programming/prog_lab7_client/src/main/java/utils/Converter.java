package utils;

import annotations.converterAnnotations.AutomaticGenerated;
import annotations.converterAnnotations.NotWrite;
import exceptions.AnnotationException;
import exceptions.ConvertInstructionException;
import exceptions.EndOfFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Класс конвертор объекета в массив строк и обратно
 * @param <T> тип объекта
 */
public class Converter<T> {
    private final Class<?> clazz;
    private static final Map<Class<?>, CheckedFunction<String, Object, ConvertInstructionException>> toObjectConvertInstructions = new HashMap<>();
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

    private static CheckedFunction<String, Object, ConvertInstructionException> getWrappedParseNumFunction(Function<String, Object> parseFunction) {
        return string -> {
            try {
                return parseFunction.apply(string);
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
    public void addInstruction(Class<?> clazz, CheckedFunction<String, Object, ConvertInstructionException> toObjectInterface) {
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
    public T inputStreamToObject(InputStream inputStream, Consumer<String> printer)
            throws IllegalAccessException, InstantiationException, EndOfFileException, IOException, AnnotationException {
        Object object = clazz.newInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(NotWrite.class) && !field.isAnnotationPresent(AutomaticGenerated.class)) {
                field.setAccessible(true);
                if (toObjectConvertInstructions.containsKey(field.getType())) {
                    while (true) {
                        printer.accept("Введите значение для поля " + field.getName());
                        if (possibleValuesStrings.containsKey(field.getType()))
                            printer.accept("Возможные значения: " + possibleValuesStrings.get(field.getType()));
                        if (Objects.isNull(line = reader.readLine())) throw new EndOfFileException();

                        try {
                            Object objValue = line.equals("") ? null : toObjectConvertInstructions.get(field.getType()).apply(line);
                            if (Validator.validateField(field, objValue)) {
                                field.set(object, objValue);
                                break;
                            }
                            else printer.accept("Значение не коректно. Попробуйте еще раз");
                        } catch (ConvertInstructionException | IllegalArgumentException e) {
                            printer.accept("Не верный формат");
                            printer.accept(e.getMessage());
                        }
                    }
                } else {
                    printer.accept("Введите значения для полей " + field.getName() + ":");
                    Converter<Object> subConverter = new Converter<>(field.getType());
                    field.set(object, subConverter.inputStreamToObject(inputStream, printer));
                }
            }
        }
        return (T) object;
    }
}
