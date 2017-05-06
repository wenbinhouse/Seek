package wb.app.seek.common.http.exception;

/**
 * Created by W.b on 16/9/27.
 */
public class ApiException extends RuntimeException {

    private String mMsg;

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    public String getMsg() {
        return mMsg;
    }
}
