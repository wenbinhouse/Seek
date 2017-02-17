package wb.app.seek.modules.setting;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import wb.app.seek.R;

/**
 * Created by W.b on 2017/2/16.
 */
public class SettingPreferenceFragment extends PreferenceFragment {

  public static SettingPreferenceFragment newInstance() {
    return new SettingPreferenceFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    addPreferencesFromResource(R.xml.preference_setting);
  }
}
