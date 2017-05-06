package wb.app.seek.common.http.rx;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import wb.app.seek.common.http.exception.ApiException;
import wb.app.seek.common.http.exception.ExceptionHandler;

/**
 * Created by W.b on 2017/2/28.
 */
public class ResponseBodyTransformer implements Observable.Transformer<ResponseBody, String> {

    private static ResponseBodyTransformer mResponseBodyTransformer = null;

    private BehaviorSubject<LifecycleEvent> mLifecycleSubject;

    private ResponseBodyTransformer() {
    }

    public static ResponseBodyTransformer getInstance(BehaviorSubject<LifecycleEvent> lifecycleSubject) {
        if (mResponseBodyTransformer == null) {
            synchronized (ResponseBodyTransformer.class) {
                if (mResponseBodyTransformer == null) {
                    mResponseBodyTransformer = new ResponseBodyTransformer();
                }
            }
        }

        mResponseBodyTransformer.setBehaviorSubject(lifecycleSubject);

        return mResponseBodyTransformer;
    }

    private void setBehaviorSubject(BehaviorSubject<LifecycleEvent> lifecycleSubject) {
        mLifecycleSubject = lifecycleSubject;
    }

    @Override
    public Observable<String> call(Observable<ResponseBody> responseBodyObservable) {
        Observable<LifecycleEvent> lifecycleEventObservable = mLifecycleSubject.takeFirst(new Func1<LifecycleEvent, Boolean>() {
            @Override
            public Boolean call(LifecycleEvent lifecycleEvent) {
                return LifecycleEvent.DESTROY.equals(lifecycleEvent);
            }
        });

        return responseBodyObservable
                .takeUntil(lifecycleEventObservable)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        if (responseBody == null) {
                            throw new ApiException("接口异常");
                        }

                        String response = "";
                        try {
                            response = responseBody.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return response;
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Throwable throwable) {
                        return Observable.error(ExceptionHandler.handle(throwable));
                    }
                });
    }
}
