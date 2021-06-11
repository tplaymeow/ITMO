package exceptions;

public class ConvertInstructionException extends Exception {
    public ConvertInstructionException() {
        super("Возвращаемое значение и тип не совпадают");
    }

    public ConvertInstructionException(String message) {
        super(message);
    }
}
