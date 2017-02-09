package wb.app.seek.common.http.rx;

import rx.Observable;
import rx.functions.Func1;
import wb.app.seek.common.http.exception.ExceptionHandler;

/**
 * 拦截服务器请求错误
 * <p>
 * Created by W.b on 16/9/25.
 */
public class HttpResponseFunc<T> implements Func1<Throwable, Observable<T>> {

  @Override
  public Observable<T> call(Throwable throwable) {
    return Observable.error(ExceptionHandler.handle(throwable));
  }
}
