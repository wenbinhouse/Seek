package wb.app.seek.common.http.rx;

import rx.Subscriber;
import wb.app.library.MLog;
import wb.app.seek.common.http.exception.ResponseException;

/**
 * Created by W.b on 16/9/25.
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private String TAG = "BaseSubscriber";

    public abstract void onSuccess(T data);

    public abstract void onFailure(String msg, int errorCode);

    public abstract void onFinish();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
        //请求完成
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        //统一处理请求失败
        onFinish();
        if (e instanceof ResponseException) {
            ResponseException throwable = (ResponseException) e;
            MLog.d(TAG, "onError() : " + throwable.getMessage());

            onFailure(throwable.getMessage(), throwable.getErrorCode());
        }
    }

    @Override
    public void onNext(T t) {
        //请求成功
        onSuccess(t);
    }
}
