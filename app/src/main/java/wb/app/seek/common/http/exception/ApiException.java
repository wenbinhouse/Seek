package wb.app.seek.common.http.exception;

/**
 * Created by W.b on 16/9/27.
 */
public class ApiException extends RuntimeException {

  private String mMsg;
  private String mException;

  public ApiException(String msg, String exception) {
    mMsg = msg;
    mException = exception;
  }

  public ApiException(String detailMessage) {
    super(detailMessage);
  }

  public String getMsg() {
    return mMsg;
  }

  public String getException() {
    return mException;
  }
}
