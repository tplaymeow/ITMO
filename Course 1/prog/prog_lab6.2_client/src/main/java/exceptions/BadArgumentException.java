package exceptions;

public class BadArgumentException extends Exception {
    public BadArgumentException() {
        super("Не верное количество аргументов.");
    }

    public BadArgumentException(String message) {
        super(message);
    }
}
