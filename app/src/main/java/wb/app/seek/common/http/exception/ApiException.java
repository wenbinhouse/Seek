package wb.app.seek.common.http.exception;

/**
 * 服务器接口返回数据异常
 * <p>
 * Created by W.b on 16/9/27.
 */
public class ApiException extends RuntimeException {

    private String mMsg;

    public ApiException(String detailMessage) {
        super(detailMessage);
        mMsg = detailMessage;
    }

    public String getMsg() {
        return mMsg;
    }
}
