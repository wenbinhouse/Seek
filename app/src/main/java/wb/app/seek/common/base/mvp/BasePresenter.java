package wb.app.seek.common.base.mvp;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import wb.app.seek.common.http.AppService;
import wb.app.seek.common.http.exception.ExceptionHandler;
import wb.app.seek.common.http.retrofit.AppClient;
import wb.app.seek.common.http.rx.LifecycleEvent;

/**
 * Created by W.b on 2016/11/29.
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

  protected String TAG = getClass().getSimpleName();

  private final BehaviorSubject<LifecycleEvent> mLifecycleSubject = BehaviorSubject.create();

  private V mView;
  private AppClient mAppClient;

  /**
   * @return
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
    this.mView = view;
    mAppClient = AppClient.getInstance();
    mService = mAppClient.getService();
  }

  @Override
  public void detachView() {
    this.mView = null;
    mLifecycleSubject.onNext(LifecycleEvent.DESTROY);
  }

  public <T> Observable.Transformer<T, T> bindLifecycleEvent() {
    return new Observable.Transformer<T, T>() {
      @Override
      public Observable<T> call(Observable<T> observable) {
        Observable<LifecycleEvent> lifecycleEventObservable = mLifecycleSubject.takeFirst(new Func1<LifecycleEvent, Boolean>() {
          @Override
          public Boolean call(LifecycleEvent activityLifeCycleEvent) {
            return activityLifeCycleEvent.equals(LifecycleEvent.DESTROY);
          }
        });

        return observable.takeUntil(lifecycleEventObservable)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(new Func1<T, T>() {
              @Override
              public T call(T t) {
                // do something

//                if (t instanceof Pojo) {
//                  Pojo pojo = (Pojo) t;
//                  String request = pojo.getRequest();
//                  String error = pojo.getError();
//                  String errorCode = pojo.getError_code();
//                  if (!TextUtils.isEmpty(error)) {
//                    MLog.d("request = " + request + ", error = " + error + ", errorCode = " + errorCode);
//                    throw new ApiException(error, errorCode);
//                  }
//                }
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
    };
  }

//  public <T> Observable.Transformer<BaseResponse<T>, T> bindLifecycleEvent() {
//
//    return new Observable.Transformer<BaseResponse<T>, T>() {
//      @Override
//      public Observable<T> call(Observable<BaseResponse<T>> baseResponseObservable) {
//        Observable<LifecycleEvent> lifecycleEventObservable = mLifecycleSubject.takeFirst(new Func1<LifecycleEvent, Boolean>() {
//          @Override
//          public Boolean call(LifecycleEvent activityLifeCycleEvent) {
//            return activityLifeCycleEvent.equals(LifecycleEvent.DESTROY);
//          }
//        });
//
//        return baseResponseObservable
//            .takeUntil(lifecycleEventObservable)
//            .compose(new CommonTransformer<T>());
//      }
//    };
//  }
}
