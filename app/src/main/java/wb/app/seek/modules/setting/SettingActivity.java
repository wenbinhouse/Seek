package wb.app.seek.modules.setting;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import wb.app.seek.R;
import wb.app.seek.common.base.BasePreferenceActivity;

/**
 * 设置
 * <p>
 * Created by W.b on 2017/2/13.
 */
public class SettingActivity extends BasePreferenceActivity {

  @Override
  protected Fragment getPreferenceFragment() {
    return SettingPreferenceFragment.newInstance();
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setTitle(R.string.setting_title);
  }
}