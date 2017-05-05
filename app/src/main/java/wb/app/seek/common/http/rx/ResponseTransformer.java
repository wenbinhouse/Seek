package wb.app.seek.common.http.rx;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/**
 * 结合了 Activity、Fragment 的生命周期，防止内存泄漏
 * <p>
 * Created by W.b on 2017/2/28.
 */
public class ResponseTransformer<T> implements Observable.Transformer<T, T> {

    private static ResponseTransformer mResponseTransformer = null;

    private BehaviorSubject<LifecycleEvent> mLifecycleSubject;

    private ResponseTransformer() {
    }

    public static <T> ResponseTransformer<T> getInstance(BehaviorSubject<LifecycleEvent> lifecycleSubject) {
        if (mResponseTransformer == null) {
            synchronized (ResponseTransformer.class) {
                if (mResponseTransformer == null) {
                    mResponseTransformer = new ResponseTransformer();
                }
            }
        }

        mResponseTransformer.setBehaviorSubject(lifecycleSubject);

        return mResponseTransformer;
    }

    private void setBehaviorSubject(BehaviorSubject<LifecycleEvent> lifecycleSubject) {
        mLifecycleSubject = lifecycleSubject;
    }

    @Override
    public Observable<T> call(Observable<T> tObservable) {
        Observable<LifecycleEvent> lifecycleEventObservable = mLifecycleSubject.takeFirst(new Func1<LifecycleEvent, Boolean>() {
            @Override
            public Boolean call(LifecycleEvent lifecycleEvent) {
                return lifecycleEvent.equals(LifecycleEvent.DESTROY);
            }
        });

        return tObservable.takeUntil(lifecycleEventObservable)
                .compose(new SchedulersTransformer<T>())
                .compose(ErrorTransformer.<T>getInstance());
    }
}
