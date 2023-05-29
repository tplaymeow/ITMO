package exceptions;

/**
 * Исключение количества аргументов команды
 */
public class ArgumentsCountException extends Exception {
    public ArgumentsCountException(String message) {
        super(message);
    }

    public ArgumentsCountException() {
        super("Не верное количество аргументов");
    }
}
