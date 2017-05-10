package wb.app.seek.modules.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseActivity;

/**
 * Created by W.b on 2017/1/9.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initComponents() {
        launchMain();
    }

    @Override
    protected boolean isContentViewWithToolbar() {
        return false;
    }

    private void launchMain() {
        // 延时进入主页
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        startActivity(MainActivity.class);
                        finish();
                    }
                });
    }
}
