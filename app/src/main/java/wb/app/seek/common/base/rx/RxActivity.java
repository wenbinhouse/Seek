package wb.app.seek.common.base.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import wb.app.seek.common.rxbus.RxBus;
import wb.app.seek.common.rxbus.RxEvent;

/**
 * 一些 RxJava 操作
 * <p>
 * Created by W.b on 10/05/2017.
 */
public class RxActivity extends AppCompatActivity {

    // 保存 RxBus 事件，当界面销毁时解除订阅关系
    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.clear();
    }

    /**
     * 发送事件
     */
    protected void postEvent(RxEvent rxEvent) {
        RxBus.getInstance().post(rxEvent);
    }

    /**
     * 注册事件监听
     */
    protected void registerEvent(Consumer<RxEvent> onNext) {
        addDispose(RxBus.getInstance().toObservable(RxEvent.class)
                .subscribe(onNext));
    }

    /**
     * 添加事件 Disposable，当界面销毁时解除订阅，防止内存泄漏
     */
    private void addDispose(Disposable disposable) {
        mDisposable.add(disposable);
    }
}
