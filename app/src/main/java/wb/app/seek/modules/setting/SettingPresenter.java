package wb.app.seek.modules.setting;

import android.app.Activity;
import android.preference.Preference;

import wb.app.seek.R;
import wb.app.seek.common.base.BaseApplication;
import wb.app.seek.common.utils.SPUtils;

/**
 * Created by W.b on 2017/2/16.
 */
public class SettingPresenter implements SettingContract.Presenter {

  private final Activity mActivity;
  private final SettingContract.View mView;
  private final SPUtils mSpUtils;

  public SettingPresenter(Activity activity, SettingContract.View view) {
    mActivity = activity;
    mView = view;

    mSpUtils = BaseApplication.getInstance().getHelper().getSpUtils();
  }

  @Override
  public void setInAppBrowser(Preference preference) {
    String key = mActivity.getString(R.string.setting_in_app_browser_key);
    mSpUtils.putBoolean(key, preference.getSharedPreferences().getBoolean(key, false));
  }

  @Override
  public void setNightMode(Preference preference) {
//    mSpUtils.putBoolean(SPKey.UI_MODE, preference.getSharedPreferences().getBoolean(SPKey.UI_MODE, false));
//
//    if ((mActivity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
//        == Configuration.UI_MODE_NIGHT_YES) {
//      mSpUtils.putInt(SPKey.UI_MODE, AppCompatDelegate.MODE_NIGHT_NO);
//      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//    } else {
//      mSpUtils.putInt(SPKey.UI_MODE, AppCompatDelegate.MODE_NIGHT_YES);
//      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//    }
//    mActivity.getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
//    mActivity.recreate();
  }
}
