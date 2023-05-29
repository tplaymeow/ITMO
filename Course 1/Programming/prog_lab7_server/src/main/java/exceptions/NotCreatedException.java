package exceptions;

public class NotCreatedException extends Exception {
    public NotCreatedException() {
        super("Ты не сформи");
    }

    public NotCreatedException(String message) {
        super(message);
    }
}
