package wb.app.seek.common.http.entity;

import java.io.Serializable;

/**
 * Created by W.b on 2017/1/9.
 */
public class BaseResponse<T> implements Serializable {

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
}
