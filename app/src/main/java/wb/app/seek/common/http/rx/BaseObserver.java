package wb.app.seek.common.http.rx;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import wb.app.library.MLog;
import wb.app.seek.common.http.exception.ResponseException;

/**
 * Created by W.b on 09/05/2017.
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private static final String TAG = "BaseObserver ";

    private Disposable mDisposable;

    //成功回调
    public abstract void onSuccess(T data);

    //失败回调
    public abstract void onFailure(String msg, int errorCode);

    //结束回调
    public abstract void onFinish();

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(@NonNull T t) {
        MLog.d(TAG + "onNext : disposed = " + mDisposable.isDisposed());

        if (!mDisposable.isDisposed()) {
            onSuccess(t);
        } else {
            onFinish();
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof ResponseException) {
            ResponseException exception = (ResponseException) e;
            onFailure(exception.getMessage(), exception.getErrorCode());
        } else {
            onFailure("未知异常", -1);
        }
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
