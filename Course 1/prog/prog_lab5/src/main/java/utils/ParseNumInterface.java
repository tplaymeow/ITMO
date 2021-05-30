package utils;

/**
 * Интерфейс для парсера
 */
@FunctionalInterface
public interface ParseNumInterface {
    Object parse(String string) throws NumberFormatException;
}
