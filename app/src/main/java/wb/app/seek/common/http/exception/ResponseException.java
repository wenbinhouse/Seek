package wb.app.seek.common.http.exception;

/**
 * Created by W.b on 16/9/27.
 */
public class ResponseException extends RuntimeException {

    private String mMessage;
    private int mErrorCode;

    public ResponseException(Throwable throwable, String message, int errorCode) {
        super(throwable);
        mMessage = message;
        mErrorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }

    public int getErrorCode() {
        return mErrorCode;
    }
}
