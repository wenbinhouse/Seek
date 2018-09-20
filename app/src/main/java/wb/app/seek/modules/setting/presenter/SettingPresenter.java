package wb.app.seek.modules.setting.presenter;

import android.app.Activity;
import android.widget.CheckBox;

import wb.app.seek.common.base.BaseApplication;
import wb.app.seek.common.base.mvp.BasePresenter;
import wb.app.seek.common.rxbus.RxBus;
import wb.app.seek.common.rxbus.RxEvent;
import wb.app.seek.common.rxbus.RxEventType;
import wb.app.seek.common.utils.SPKey;
import wb.app.seek.common.utils.SPUtils;
import wb.app.seek.modules.setting.SettingActivity;

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
        ((SettingActivity) getView()).getBrowserInAppView().getCheckBox().setChecked(mSpUtils.getBoolean(SPKey.IN_APP_BROWSER));

        ((SettingActivity) getView()).getNightModeView().getCheckBox().setChecked(mSpUtils.getBoolean(SPKey.DAY_NIGHT_MODE));
    }

    @Override
    public void setInAppBrowser() {
        CheckBox checkBox = ((SettingActivity) getView()).getBrowserInAppView().getCheckBox();

        boolean isChecked = checkBox.isChecked();

        checkBox.setChecked(!isChecked);

        mSpUtils.putBoolean(SPKey.IN_APP_BROWSER, !isChecked);
    }

    @Override
    public void setNightMode() {
        CheckBox checkBox = ((SettingActivity) getView()).getNightModeView().getCheckBox();

        boolean isChecked = checkBox.isChecked();

        checkBox.setChecked(!isChecked);

        mSpUtils.putBoolean(SPKey.DAY_NIGHT_MODE, !isChecked);

        RxBus.getInstance().post(new RxEvent(RxEventType.DAY_NIGHT_MODE, String.valueOf(!isChecked)));

        ((SettingActivity) getView()).finish();
    }
}
