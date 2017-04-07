package wb.app.seek.modules.setting;

import android.app.Activity;
import android.widget.CheckBox;

import wb.app.seek.R;
import wb.app.seek.common.base.BaseApplication;
import wb.app.seek.common.base.mvp.BasePresenter;
import wb.app.seek.common.rxbus.RxBus;
import wb.app.seek.common.rxbus.RxEvent;
import wb.app.seek.common.rxbus.RxEventType;
import wb.app.seek.common.utils.SPUtils;

/**
 * Created by W.b on 2017/2/16.
 */
public class SettingPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter {

  private final Activity mActivity;
  private final SPUtils mSpUtils;

  public SettingPresenter(Activity activity) {
    mActivity = activity;

    mSpUtils = BaseApplication.getInstance().getHelper().getSpUtils();
  }

  @Override
  public void initView() {
    String browserKey = mActivity.getString(R.string.setting_in_app_browser_key);
    ((SettingActivity) getView()).getBrowserInAppView().getCheckBox().setChecked(mSpUtils.getBoolean(browserKey));

    String nightModeKey = mActivity.getString(R.string.night_mode_key);
    ((SettingActivity) getView()).getNightModeView().getCheckBox().setChecked(mSpUtils.getBoolean(nightModeKey));
  }

  @Override
  public void setInAppBrowser() {
    CheckBox checkBox = ((SettingActivity) getView()).getBrowserInAppView().getCheckBox();
    String key = mActivity.getString(R.string.setting_in_app_browser_key);

    boolean isChecked = mSpUtils.getBoolean(key);

    checkBox.setChecked(!isChecked);

    mSpUtils.putBoolean(key, !isChecked);
  }

  @Override
  public void setNightMode() {
    CheckBox checkBox = ((SettingActivity) getView()).getNightModeView().getCheckBox();
    String key = mActivity.getString(R.string.night_mode_key);

    boolean isChecked = mSpUtils.getBoolean(key);

    checkBox.setChecked(!isChecked);

    mSpUtils.putBoolean(key, !isChecked);

    RxBus.getInstance().post(new RxEvent(RxEventType.DAY_NIGHT_MODE, String.valueOf(!isChecked)));

    ((SettingActivity) getView()).finish();
  }
}
