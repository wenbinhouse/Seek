package wb.app.seek.common.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import wb.app.seek.common.http.rx.RxSchedulers;

/**
 * Created by W.b on 2017/3/6.
 */
public class RxBus {

    private static RxBus mInstance;
    //    private final Subject<Object, Object> mBus;
    private PublishSubject<Object> mBus;

    private RxBus() {
//        mBus = new SerializedSubject<>(PublishSubject.create());
        mBus = PublishSubject.create();
    }

    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }

        return mInstance;
    }

    public void post(Object o) {
        mBus.onNext(o);
    }

    public <T> Observable<T> toObservable(Class<T> event) {
        return mBus.ofType(event)
                .compose(RxSchedulers.<T>io2Main());
    }

//    public <T> Observable<T> toObservable(Class<T> eventType) {
//        return mBus.ofType(eventType);
//    }
}
