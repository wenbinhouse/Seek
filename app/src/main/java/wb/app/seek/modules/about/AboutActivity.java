package wb.app.seek.modules.about;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import wb.app.seek.R;
import wb.app.seek.common.base.BasePreferenceActivity;

/**
 * 关于
 * <p>
 * Created by W.b on 2017/2/13.
 */
public class AboutActivity extends BasePreferenceActivity {

  @Override
  protected Fragment getPreferenceFragment() {
    return AboutPreferenceFragment.newInstance();
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setTitle(R.string.about_title);
  }
}
