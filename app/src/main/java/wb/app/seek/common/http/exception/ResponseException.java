package wb.app.seek.common.http.exception;

/**
 * Created by W.b on 16/9/27.
 */
public class ResponseException extends RuntimeException {

  public String mMessage;
  private String mException;

  public ResponseException(Throwable throwable, String message) {
    super(throwable);
    mMessage = message;
  }

  public ResponseException(Throwable throwable, String msg, String exception) {
    super(throwable);
    mMessage = msg;
    mException = exception;
  }

  @Override
  public String getMessage() {
    return mMessage;
  }

  public String getException() {
    return mException;
  }
}
