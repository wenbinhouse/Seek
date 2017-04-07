package wb.app.seek.modules.about;

import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import wb.app.seek.R;
import wb.app.seek.common.base.mvp.MvpActivity;
import wb.app.seek.widgets.SettingItemView;

/**
 * 关于
 * <p>
 * Created by W.b on 2017/2/13.
 */
public class AboutActivity extends MvpActivity<AboutPresenter> implements AboutContract.View {

  @BindView(R.id.versionView) SettingItemView mVersionView;
  @BindView(R.id.authorView) SettingItemView mAuthorView;
  @BindView(R.id.githubView) SettingItemView mGithubView;
  @BindView(R.id.jianshuView) SettingItemView mJianshuView;
  @BindView(R.id.feedbackView) SettingItemView mFeedbackView;
  @BindView(R.id.licenseView) SettingItemView mLicenseView;

  @Override
  public void showLoading() {

  }

  @Override
  public void hideLoading() {

  }

  @Override
  public void showRateError() {

  }

  @Override
  public void showFeedbackError() {

  }

  @Override
  public void showBrowserNotFoundError() {

  }

  @Override
  public void showDonateToast() {
    showToastShort("复制成功，请进入支付宝完成捐赠");
  }

  @Override
  protected AboutPresenter createPresenter() {
    return new AboutPresenter(this);
  }

  @Override
  protected int getContentViewId() {
    return R.layout.activity_about;
  }

  @Override
  protected void initComponents() {

  }

  @OnClick({R.id.versionView, R.id.authorView, R.id.githubView, R.id.jianshuView, R.id.feedbackView, R.id.licenseView})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.versionView:
        break;

      case R.id.authorView:
        break;

      case R.id.githubView:
        getPresenter().followOnGithub();
        break;

      case R.id.jianshuView:
        getPresenter().followOnJianshu();
        break;

      case R.id.feedbackView:
        getPresenter().feedback();
        break;

      case R.id.licenseView:
        getPresenter().openLicense();
        break;
    }
  }
}
