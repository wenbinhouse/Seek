package wb.app.seek.modules.setting;

import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import wb.app.seek.R;
import wb.app.seek.common.base.mvp.MvpActivity;
import wb.app.seek.modules.setting.presenter.SettingContract;
import wb.app.seek.modules.setting.presenter.SettingPresenter;
import wb.app.seek.widgets.SettingItemView;

/**
 * 设置
 * <p>
 * Created by W.b on 2017/2/13.
 */
public class SettingActivity extends MvpActivity<SettingPresenter> implements SettingContract.View {

    @BindView(R.id.browserInAppView) SettingItemView mBrowserInAppView;
    @BindView(R.id.nightModeView) SettingItemView mNightModeView;

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initComponents() {
        getPresenter().initView();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public SettingItemView getBrowserInAppView() {
        return mBrowserInAppView;
    }

    public SettingItemView getNightModeView() {
        return mNightModeView;
    }

    @OnClick({R.id.browserInAppView, R.id.nightModeView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.browserInAppView:
                getPresenter().setInAppBrowser();
                break;

            case R.id.nightModeView:
                getPresenter().setNightMode();
                break;
        }
    }
}
