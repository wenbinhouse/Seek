package wb.app.seek.common.http.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.BehaviorSubject;
import wb.app.library.MLog;
import wb.app.seek.common.http.exception.ApiException;
import wb.app.seek.common.http.exception.ExceptionHandler;

/**
 * 生命周期绑定
 * <p>
 * Created by W.b on 10/05/2017.
 */
public class LifecycleTransformer<T> implements ObservableTransformer<T, T> {

    private static final String TAG = "LifecycleTransformer ";

    private BehaviorSubject<LifecycleEvent.PresenterLifecycle> mLifecycleEventBehaviorSubject;

    public LifecycleTransformer(BehaviorSubject<LifecycleEvent.PresenterLifecycle> behaviorSubject) {
        mLifecycleEventBehaviorSubject = behaviorSubject;
    }

    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        Observable<LifecycleEvent.PresenterLifecycle> lifecycleObservable = mLifecycleEventBehaviorSubject.filter(new Predicate<LifecycleEvent.PresenterLifecycle>() {
            @Override
            public boolean test(@NonNull LifecycleEvent.PresenterLifecycle presenterLifecycle) throws Exception {
                MLog.d(TAG, "LifecycleTransformer : presenterLifecycle = " + presenterLifecycle);
                return presenterLifecycle.equals(LifecycleEvent.PresenterLifecycle.DETACH_VIEW);
            }
        });
        return upstream.takeUntil(lifecycleObservable)
                .compose(RxSchedulers.<T>io2Main())
                .map(new Function<T, T>() {
                    @Override
                    public T apply(@NonNull T t) throws Exception {
                        // RxJava2 不允许 null 数据
                        if (t == null) {
                            throw new ApiException("接口出错");
                        }
                        return t;
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(@NonNull Throwable throwable) throws Exception {
                        // 处理异常
                        return Observable.error(ExceptionHandler.handle(throwable));
                    }
                });
    }
}
