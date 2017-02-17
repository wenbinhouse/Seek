package wb.app.seek.common.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import wb.app.seek.R;

/**
 * Created by W.b on 2017/2/16.
 */
public abstract class BasePreferenceActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_preference_base);

    ButterKnife.bind(this);

    getFragmentManager()
        .beginTransaction()
        .add(R.id.content_fl, getPreferenceFragment())
        .commit();
  }

  protected abstract Fragment getPreferenceFragment();
}
