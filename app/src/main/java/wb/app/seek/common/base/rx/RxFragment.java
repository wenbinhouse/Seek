package wb.app.seek.common.base.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import wb.app.seek.common.rxbus.RxEvent;

/**
 * 一些 RxJava 操作
 * <p>
 * Created by W.b on 10/05/2017.
 */
public class RxFragment extends Fragment {

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
     * 添加事件 Disposable，界面销毁清空，防止内存泄漏
     */
    private void addDispose(Disposable disposable) {
        mDisposable.add(disposable);
    }
}
