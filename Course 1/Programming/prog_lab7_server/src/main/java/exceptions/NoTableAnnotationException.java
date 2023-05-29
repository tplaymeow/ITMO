package exceptions;

public class NoTableAnnotationException extends Exception{
    public NoTableAnnotationException(String message) {
        super(message);
    }

    public NoTableAnnotationException() {
        super("У класса нет аннотации 'Table'");
    }
}