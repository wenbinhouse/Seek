package wb.app.seek.common.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import wb.app.seek.R;

/**
 * Created by W.b on 2017/2/16.
 */
public abstract class BasePreferenceActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected int getContentViewId() {
    return R.layout.activity_preference_base;
  }

  @Override
  protected void initComponents() {
    getFragmentManager()
        .beginTransaction()
        .add(R.id.content_fl, getPreferenceFragment())
        .commit();
  }

  protected abstract Fragment getPreferenceFragment();
}
