package wb.app.seek.common.http.rx;

import rx.Subscriber;
import wb.app.seek.common.http.exception.ResponseException;
import wb.app.seek.common.utils.mlog.MLog;

/**
 * Created by W.b on 16/9/25.
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

  private String TAG = "BaseSubscriber";

  public abstract void onSuccess(T data);

  public abstract void onFailure(String msg, String exception);

  public abstract void onFinish();

  @Override
  public void onStart() {
    super.onStart();
//    LogUtils.d(TAG, "onStart()");
  }

  @Override
  public void onCompleted() {
    //请求完成
//    LogUtils.d(TAG, "onCompleted()");
    onFinish();
  }

  @Override
  public void onError(Throwable e) {
    //统一处理请求失败
    if (e instanceof ResponseException) {
      ResponseException throwable = (ResponseException) e;
      MLog.d(TAG, "onError() : " + throwable.getMessage());

      onFailure(throwable.getMessage(), throwable.getException());
    } else {
      MLog.d(TAG, "onError() : " + "未知异常");

      onFailure("未知异常", "未知异常");
    }

    onFinish();
  }

  @Override
  public void onNext(T t) {
    //请求成功
//    LogUtils.d(TAG, "onNext()");
    onSuccess(t);
  }
}
