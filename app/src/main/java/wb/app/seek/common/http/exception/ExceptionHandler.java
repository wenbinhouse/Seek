package wb.app.seek.common.http.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import wb.app.library.MLog;

/**
 * 异常处理
 * <p>
 * Created by W.b on 16/9/25.
 */
public class ExceptionHandler {

    private static String TAG = "ExceptionHandler";
    private static final String SOCKETTIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";

    public static final int API_EXCEPTION = 0;
    public static final int NETWORK_EXCEPTION = 1;
    public static final int PARSE_EXCEPTION = 2;
    public static final int UNKNOWN_EXCEPTION = 3;

    public static ResponseException handle(Throwable e) {
        MLog.d(TAG, "exception : " + e.getClass().getSimpleName() + ", message : " + e.getMessage());
        ResponseException ex = null;
        if (e instanceof IllegalStateException) {

        } else if (e instanceof ConnectException || e.getCause() instanceof ConnectException
                || e instanceof UnknownHostException || e.getCause() instanceof UnknownHostException) {
            ex = new ResponseException(e, "网络连接异常，请检查您的网络状态", NETWORK_EXCEPTION);

        } else if (e instanceof SocketTimeoutException) {
            ex = new ResponseException(e, "网络连接超时，请检查您的网络状态", NETWORK_EXCEPTION);

        } else if (e instanceof HttpException) {
            ex = new ResponseException(e, "网络连接异常，请检查您的网络状态", NETWORK_EXCEPTION);

        } else if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            String msg = apiException.getMsg();
            ex = new ResponseException(apiException, msg, API_EXCEPTION);

        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponseException(e, "数据解析异常，稍后重试", PARSE_EXCEPTION);

        }
//        else if (e instanceof SSLHandshakeException) {
//            ex = new ResponseException(e, "证书验证错误");
//
//        }
        else {
            ex = new ResponseException(e, "未知错误", UNKNOWN_EXCEPTION);
        }
        return ex;
    }
}
