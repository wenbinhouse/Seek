package wb.app.seek.common.http.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.adapter.rxjava.HttpException;
import wb.app.library.MLog;

/**
 * 异常处理
 * <p>
 * Created by W.b on 16/9/25.
 */
public class ExceptionHandler {

  private static String TAG = "ExceptionHandler";

  public static ResponseException handle(Throwable e) {
    MLog.d(TAG, "exception : " + e.getClass().getSimpleName() + ", message : " + e.getMessage());
    ResponseException ex = null;
    if (e instanceof IllegalStateException) {

    } else if (e instanceof ConnectException || e.getCause() instanceof ConnectException
        || e instanceof UnknownHostException || e.getCause() instanceof UnknownHostException) {
      ex = new ResponseException(e, "网络出错,请检查网络");

    } else if (e instanceof SocketTimeoutException) {
      ex = new ResponseException(e, "网络出错,请检查网络");

    } else if (e instanceof HttpException) {
      ex = new ResponseException(e, "网络出错,请检查网络");

    } else if (e instanceof ApiException) {
      ApiException apiException = (ApiException) e;
      String msg = apiException.getMsg();
      String exception = apiException.getException();
      ex = new ResponseException(apiException, msg);

    } else if (e instanceof JsonParseException
        || e instanceof JSONException
        || e instanceof ParseException) {
      ex = new ResponseException(e, "解析错误");

    } else if (e instanceof SSLHandshakeException) {
      ex = new ResponseException(e, "证书验证错误");

    } else {
      ex = new ResponseException(e, "未知错误");
    }
    return ex;
  }
}
