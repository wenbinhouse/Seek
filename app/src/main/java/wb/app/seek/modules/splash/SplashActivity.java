package wb.app.seek.modules.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
    Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
        .map(new Func1<Long, Object>() {
          @Override
          public Object call(Long aLong) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
            return null;
          }
        }).subscribe();
  }
}
