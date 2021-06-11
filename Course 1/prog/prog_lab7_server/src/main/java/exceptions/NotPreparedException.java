package exceptions;

public class NotPreparedException extends Exception {
    public NotPreparedException() {
        super("Мы еще не готовы :-) давайте подготовимся, запусти метод prepare(), коть :3");
    }

    public NotPreparedException(String message) {
        super(message);
    }
}
