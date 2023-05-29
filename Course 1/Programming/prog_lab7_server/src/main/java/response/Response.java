package response;

import java.io.Serializable;

public class Response implements Serializable {
    private final boolean isSuccess;
    private final String message;

    public Response(String message, boolean isSuccess) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    // Getters
    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }
}
