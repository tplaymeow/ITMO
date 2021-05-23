package exceptions;

/**
 * Исключения невозможности записи
 */
public class CantWriteException extends Exception {
    public CantWriteException() {
        super("Запись в файл невозможна");
    }
}
