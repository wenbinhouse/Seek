package wb.app.seek.modules.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.Preference;

import wb.app.seek.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by W.b on 2017/2/16.
 */
public class SettingPresenter implements SettingContract.Presenter {

  private final Activity mActivity;
  private final SettingContract.View mView;
  private final SharedPreferences.Editor mEditor;

  public SettingPresenter(Activity activity, SettingContract.View view) {
    mActivity = activity;
    mView = view;

    SharedPreferences sp = mActivity.getSharedPreferences(mActivity.getString(R.string.shared_preferences_key), MODE_PRIVATE);
    mEditor = sp.edit();
  }

  @Override
  public void setInAppBrowser(Preference preference) {
    String key = mActivity.getString(R.string.setting_in_app_browser_key);
    mEditor.putBoolean(key, preference.getSharedPreferences().getBoolean(key, false));
    mEditor.apply();
  }
}
