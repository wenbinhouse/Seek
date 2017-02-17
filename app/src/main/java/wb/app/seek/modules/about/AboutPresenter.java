package wb.app.seek.modules.about;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

import wb.app.seek.R;
import wb.app.seek.common.utils.mlog.MLog;
import wb.app.seek.modules.customtabs.CustomTabActivityHelper;
import wb.app.seek.modules.customtabs.WebViewFallback;

/**
 * Created by W.b on 2017/2/16.
 */
public class AboutPresenter implements AboutContract.Presenter {

  private AboutContract.View mView;
  private final CustomTabsIntent.Builder mCustomTabsIntent;
  private final Activity mActivity;

  public AboutPresenter(Activity activity, AboutContract.View view) {
    mActivity = activity;
    mView = view;

    mCustomTabsIntent = new CustomTabsIntent.Builder();
    mCustomTabsIntent.setToolbarColor(Color.WHITE);
    mCustomTabsIntent.setShowTitle(true);
  }

  @Override
  public void rateInAppPLay() {
    MLog.d("rate");
  }

  @Override
  public void showEasterEgg() {
    MLog.d("easter egg");
  }

  @Override
  public void followOnGithub() {
    CustomTabActivityHelper.openCustomTab(mActivity, mCustomTabsIntent.build()
        , Uri.parse(mActivity.getString(R.string.github_summary))
        , new WebViewFallback() {
          @Override
          public void openUri(Activity activity, Uri uri) {
            super.openUri(activity, uri);
          }
        });
  }

  @Override
  public void followOnJianshu() {
    CustomTabActivityHelper.openCustomTab(mActivity, mCustomTabsIntent.build()
        , Uri.parse(mActivity.getString(R.string.jianshu_summary))
        , new WebViewFallback(){
          @Override
          public void openUri(Activity activity, Uri uri) {
            super.openUri(activity, uri);
          }
        });
  }

  @Override
  public void followOnQQ() {
  }

  @Override
  public void feedback() {
  }

  @Override
  public void donate() {
  }

  @Override
  public void openLicense() {
  }
}
