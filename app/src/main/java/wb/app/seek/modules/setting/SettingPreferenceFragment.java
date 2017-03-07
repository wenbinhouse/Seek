package wb.app.seek.modules.setting;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.View;

import wb.app.seek.R;

/**
 * Created by W.b on 2017/2/16.
 */
public class SettingPreferenceFragment extends PreferenceFragment implements SettingContract.View {

  public static SettingPreferenceFragment newInstance() {
    return new SettingPreferenceFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    addPreferencesFromResource(R.xml.preference_setting);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    final SettingPresenter presenter = new SettingPresenter(getActivity(), this);

    findPreference(getString(R.string.setting_in_app_browser_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
      @Override
      public boolean onPreferenceClick(Preference preference) {
        presenter.setInAppBrowser(preference);
        return false;
      }
    });
  }
}
