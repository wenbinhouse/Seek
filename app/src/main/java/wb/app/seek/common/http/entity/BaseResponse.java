package wb.app.seek.common.http.entity;

/**
 * Json Model
 * <p>
 * Created by mwb on 16/9/25.
 */
public class BaseResponse<T> {

  private T result;

  private String request;

  private String error_code;

  private String error;

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public String getError_code() {
    return error_code;
  }

  public void setError_code(String error_code) {
    this.error_code = error_code;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  //  private int total;
//
//  private String errorCode;
//
//  private String reason;
//
//  public T getResult() {
//    return result;
//  }
//
//  public void setResult(T result) {
//    this.result = result;
//  }
//
//  public int getTotal() {
//    return total;
//  }
//
//  public void setTotal(int total) {
//    this.total = total;
//  }
//
//  public String getErrorCode() {
//    return errorCode;
//  }
//
//  public void setErrorCode(String errorCode) {
//    this.errorCode = errorCode;
//  }
//
//  public String getReason() {
//    return reason;
//  }
//
//  public void setReason(String reason) {
//    this.reason = reason;
//  }
}
