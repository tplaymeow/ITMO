public class Request {

    // 0 - плохо, 1 - хорошо
    private int code;
    private String requestText;

    public Request(int code, String requestText) {
        this.code = code;
        this.requestText = requestText;
    }
}
