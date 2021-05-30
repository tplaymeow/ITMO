package utils;

import exceptions.ConvertInstructionException;

/**
 * Функциональный интерфейс для получения некоторых объектов из строки
 */
@FunctionalInterface
public interface ToObjectInterface {
    Object toObject(String string) throws ConvertInstructionException;
}
