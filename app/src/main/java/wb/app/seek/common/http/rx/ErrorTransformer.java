package wb.app.seek.common.http.rx;

import rx.Observable;
import rx.functions.Func1;
import wb.app.seek.common.http.exception.ApiException;
import wb.app.seek.common.http.exception.ExceptionHandler;

/**
 * 异常转换器
 * <p>
 * Created by W.b on 2017/2/28.
 */
public class ErrorTransformer<T> implements Observable.Transformer<T, T> {

    private static ErrorTransformer mErrorTransformer = null;

    private ErrorTransformer() {
    }

    public static <T> ErrorTransformer<T> getInstance() {
        if (mErrorTransformer == null) {
            synchronized (ErrorTransformer.class) {
                if (mErrorTransformer == null) {
                    mErrorTransformer = new ErrorTransformer();
                }
            }
        }

        return mErrorTransformer;
    }

    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable
                .map(new Func1<T, T>() {
                    @Override
                    public T call(T t) {
                        if (t == null) {
                            throw new ApiException("接口出错");
                        }

                        return t;
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
                    @Override
                    public Observable<? extends T> call(Throwable throwable) {
                        return Observable.error(ExceptionHandler.handle(throwable));
                    }
                });
    }
}
