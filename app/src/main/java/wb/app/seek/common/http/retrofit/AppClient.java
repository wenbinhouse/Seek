package wb.app.seek.common.http.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wb.app.seek.BuildConfig;
import wb.app.seek.common.http.ApiManager;
import wb.app.seek.common.http.AppService;

/**
 * Created by W.b on 16/11/25.
 */
public class AppClient {

  private OkHttpClient mOkHttpClient;
  private Retrofit mRetrofit;
  private static AppClient mAppClient = new AppClient();
  private final AppService mService;

  private AppClient() {
    initOkHttp();

    //变量名使用驼峰式，eg: user_name --> userName
//    Gson gson = new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    mRetrofit = new Retrofit.Builder()
        .client(mOkHttpClient)
        .baseUrl(ApiManager.ZHIHU_HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();

    mService = mRetrofit.create(AppService.class);
  }

  private void initOkHttp() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    if (BuildConfig.DEBUG) {
      //添加日志打印
      builder.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
    }

    //添加 Header、公共参数
//    Map<String, String> params = new HashMap<>();
//    params.put("access_token", BaseApplication.getInstance().getHelper().getAccessToken());
//    builder.addInterceptor(new BaseInterceptor(null, params));

    //设置超时时间
    builder.connectTimeout(10, TimeUnit.SECONDS);
    builder.readTimeout(15, TimeUnit.SECONDS);
    builder.writeTimeout(15, TimeUnit.SECONDS);
    builder.retryOnConnectionFailure(true);
    mOkHttpClient = builder.build();
  }

  public static AppClient getInstance() {
    return mAppClient;
  }

  public AppService getService() {
    return mService;
  }

  public <T> T create(final Class<T> service) {
    return mRetrofit.create(service);
  }
}
