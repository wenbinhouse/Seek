package wb.app.seek.common.http.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import wb.app.seek.common.http.entity.BaseResponse;

/**
 * Created by W.b on 2016/12/5.
 */
public class CommonTransformer<T> implements Observable.Transformer<BaseResponse<T>, T> {

  @Override
  public Observable<T> call(Observable<BaseResponse<T>> observable) {
    return observable.subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(ErrorTransformer.<T>getInstance());
  }
}
