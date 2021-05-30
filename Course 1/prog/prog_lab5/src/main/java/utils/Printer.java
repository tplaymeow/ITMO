package utils;

/**
 * Функ. интерфейс для функции для отправки сообщений из конвертера
 */
@FunctionalInterface
public interface Printer {
    void print(String string);
}
