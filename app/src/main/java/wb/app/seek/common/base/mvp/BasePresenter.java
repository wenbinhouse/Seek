package wb.app.seek.common.base.mvp;

import io.reactivex.subjects.BehaviorSubject;
import wb.app.seek.common.base.mvp.impl.IPresenter;
import wb.app.seek.common.base.mvp.impl.IView;
import wb.app.seek.common.http.AppService;
import wb.app.seek.common.http.retrofit.AppClient;
import wb.app.seek.common.http.rx.LifecycleEvent;

/**
 * Created by W.b on 2016/11/29.
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    protected String TAG = getClass().getSimpleName();

    protected BehaviorSubject<LifecycleEvent.PresenterLifecycle> mLifecycleEventBehaviorSubject = BehaviorSubject.create();

    private V mView;

    /**
     * @return View
     * @Exception NullPointerException
     */
    public V getView() {
        return mView;
    }

    public AppService getService() {
        return mService;
    }

    private AppService mService;

    @Override
    public void attachView(V view) {
        mLifecycleEventBehaviorSubject.onNext(LifecycleEvent.PresenterLifecycle.ATTACH_VIEW);
        this.mView = view;
        mService = AppClient.getInstance().getService();
    }

    @Override
    public void detachView() {
        mLifecycleEventBehaviorSubject.onNext(LifecycleEvent.PresenterLifecycle.DETACH_VIEW);
        this.mView = null;
    }

    @Override
    public boolean isAttachView() {
        return mView != null;
    }
}
