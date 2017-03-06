package wb.app.seek.common.http.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 线程转换器
 * <p>
 * Created by W.b on 2017/2/28.
 */
public class SchedulersTransformer<T> implements Observable.Transformer<T, T> {

  @Override
  public Observable<T> call(Observable<T> tObservable) {
    return tObservable
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
