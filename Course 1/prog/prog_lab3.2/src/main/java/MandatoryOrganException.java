public class MandatoryOrganException extends RuntimeException{
    public MandatoryOrganException() {
        super("Орган жизненно важный");
    }

    public MandatoryOrganException(String message) {
        super(message);
    }
}
