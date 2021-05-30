package exceptions;

/**
 * Исключение конца файла
 */
public class EndOfFileException extends Exception {
    public EndOfFileException() {
        super("Ошибка. Файл зкончен");
    }

    public EndOfFileException(String message) {
        super(message);
    }
}
