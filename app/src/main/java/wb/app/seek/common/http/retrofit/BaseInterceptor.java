package wb.app.seek.common.http.retrofit;

import android.text.TextUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import wb.app.seek.common.utils.mlog.MLog;

/**
 * 拦截器: 添加 header、公共参数
 * <p>
 * Created by W.b on 16/9/25.
 */
public class BaseInterceptor implements Interceptor {

  private String TAG = "BaseInterceptor";

  private Map<String, String> mHeaders;
  private Map<String, String> mParams;

  public BaseInterceptor(Map<String, String> header, Map<String, String> params) {
    this.mHeaders = header;
    this.mParams = params;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {

    Request request = chain.request();
    HttpUrl httpUrl = request.url();
    Request.Builder requestBuilder = request.newBuilder();
    HttpUrl.Builder httpUrlBuilder = httpUrl.newBuilder();

    StringBuffer sb = new StringBuffer();

    if (mHeaders != null && mHeaders.size() > 0) {
      sb.append("headers : ");
      for (String key : mHeaders.keySet()) {
        if (!TextUtils.isEmpty(mHeaders.get(key))) {
          sb.append("\nkey = " + key + ", param = " + mHeaders.get(key));
          requestBuilder.addHeader(key, mHeaders.get(key)).build();
        }
      }
    }

    if (mParams != null && mParams.size() > 0) {
      sb.append("\nparams : ");
      HttpUrl newHttpUrl = null;
      for (String key : mParams.keySet()) {
        if (!TextUtils.isEmpty(mParams.get(key))) {
          sb.append("\nkey = " + key + ", param = " + mParams.get(key));
          newHttpUrl = httpUrlBuilder.addQueryParameter(key, mParams.get(key)).build();
        }
      }
      requestBuilder.url(newHttpUrl).build();
    }

    MLog.d(TAG, sb.toString());

    return chain.proceed(requestBuilder.build());
  }
}
