package exceptions;

/**
 * Исключения возникающие при валидации
 */
public class ValidationException extends Exception {
    public ValidationException() {
        super("Какое-то из полей не прошло валидацию");
    }

    public ValidationException(String message) {
        super(message);
    }
}
