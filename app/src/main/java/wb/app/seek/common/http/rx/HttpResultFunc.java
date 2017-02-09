package wb.app.seek.common.http.rx;

import android.text.TextUtils;

import rx.functions.Func1;
import wb.app.seek.common.http.entity.BaseResponse;
import wb.app.seek.common.http.exception.ApiException;
import wb.app.seek.common.utils.mlog.MLog;

/**
 * 拦截服务器返回 Json 异常
 * <p>
 * Created by W.b on 16/9/25.
 */
public class HttpResultFunc<T> implements Func1<BaseResponse<T>, T> {

  private String TAG = "HttpResultFunc";

  @Override
  public T call(BaseResponse<T> jsonResponse) {
    String error = jsonResponse.getError();
    String errorCode = jsonResponse.getError_code();
    String request = jsonResponse.getRequest();
    if (!TextUtils.isEmpty(error)) {
      MLog.d(TAG, "error = " + error + ", errorCode = " + errorCode + ", request = " + request);
      throw new ApiException(error, errorCode);
    }
    return jsonResponse.getResult();
  }
}
