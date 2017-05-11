package wb.app.seek.common.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import wb.app.seek.common.http.rx.RxSchedulers;

/**
 * RxBus 事件
 * <p>
 * Created by W.b on 2017/3/6.
 */
public class RxBus {

    private volatile static RxBus mInstance;
    private PublishSubject<Object> mBus;

    private RxBus() {
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

    /**
     * 发送事件
     */
    public void post(Object o) {
        mBus.onNext(o);
    }

    /**
     * 建立订阅关系
     */
    public <T> Observable<T> toObservable(Class<T> event) {
        return mBus.ofType(event)
                .compose(RxSchedulers.<T>io2Main());
    }
}
