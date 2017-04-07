package wb.app.seek.modules.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseActivity;
import wb.app.seek.modules.main.MainActivity;

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
    Observable.timer(2, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Long>() {
          @Override
          public void call(Long aLong) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
          }
        });
  }
}
