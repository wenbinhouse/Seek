package wb.app.seek.common.base.mvp;

import rx.subjects.BehaviorSubject;
import wb.app.seek.common.http.AppService;
import wb.app.seek.common.http.retrofit.AppClient;
import wb.app.seek.common.http.rx.LifecycleEvent;

/**
 * Created by W.b on 2016/11/29.
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

  protected String TAG = getClass().getSimpleName();

  protected final BehaviorSubject<LifecycleEvent> mLifecycleSubject = BehaviorSubject.create();

  private V mView;
  private AppClient mAppClient;

  /**
   * @return
   * @Exception NullPointerException
   */
  public V getView() {
    return mView;
  }

  public boolean isAttach() {
    return mView != null;
  }

  public AppService getService() {
    return mService;
  }

  private AppService mService;

  @Override
  public void attachView(V view) {
    this.mView = view;
    mAppClient = AppClient.getInstance();
    mService = mAppClient.getService();
  }

  @Override
  public void detachView() {
    this.mView = null;
    mLifecycleSubject.onNext(LifecycleEvent.DESTROY);
  }
}
