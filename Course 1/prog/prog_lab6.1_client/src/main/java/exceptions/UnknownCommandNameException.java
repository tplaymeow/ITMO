package exceptions;

public class UnknownCommandNameException extends Exception {
    public UnknownCommandNameException(String message) {
        super(message);
    }

    public UnknownCommandNameException() {
        super("Команды с таким именем не существует");
    }
}
