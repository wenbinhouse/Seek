package wb.app.seek.common.http.rx;

import rx.Observable;
import wb.app.seek.common.http.entity.BaseResponse;

/**
 * Created by W.b on 2016/12/5.
 */
public class ErrorTransformer<T> implements Observable.Transformer<BaseResponse<T>, T> {

  private static ErrorTransformer mErrorTransformer = null;

  @Override
  public Observable<T> call(Observable<BaseResponse<T>> observable) {
    return observable.map(new HttpResultFunc<T>())
        .onErrorResumeNext(new HttpResponseFunc<T>());
  }

  public static <T> ErrorTransformer<T> getInstance() {
    if (mErrorTransformer == null) {
      synchronized (ErrorTransformer.class) {
        if (mErrorTransformer == null) {
          mErrorTransformer = new ErrorTransformer<>();
        }
      }
    }
    return mErrorTransformer;
  }
}
