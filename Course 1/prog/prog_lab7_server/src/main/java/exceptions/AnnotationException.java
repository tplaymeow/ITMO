package exceptions;

/**
 * Исключение для аннотаций для валидации
 */
public class AnnotationException extends Exception {
    public AnnotationException(String message) {
        super(message);
    }

    public AnnotationException() {
        super("У поля этого типа не может быть этой аннотации");
    }

    public AnnotationException(String fieldName,String annotationName) {
        super("Для поля " + fieldName + " недоступна аннотация " + annotationName);
    }
}
