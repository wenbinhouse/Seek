package wb.app.seek.modules.about;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AlertDialog;

import wb.app.seek.R;
import wb.app.seek.common.base.BaseApplication;
import wb.app.seek.common.base.mvp.BasePresenter;
import wb.app.seek.common.utils.SPUtils;
import wb.app.seek.modules.customtabs.CustomTabActivityHelper;
import wb.app.seek.modules.customtabs.WebViewFallback;
import wb.app.seek.modules.webview.WebViewActivity;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by W.b on 2017/2/16.
 */
public class AboutPresenter extends BasePresenter<AboutContract.View> implements AboutContract.Presenter {

  private final CustomTabsIntent.Builder mCustomTabsIntent;
  private final Activity mActivity;
  private final boolean mIsInAppBrowser;

  public AboutPresenter(Activity activity) {
    mActivity = activity;

    mCustomTabsIntent = new CustomTabsIntent.Builder();
    mCustomTabsIntent.setToolbarColor(Color.WHITE);
    mCustomTabsIntent.setShowTitle(true);

    SPUtils spUtils = BaseApplication.getInstance().getHelper().getSpUtils();
    mIsInAppBrowser = spUtils.getBoolean(mActivity.getString(R.string.setting_in_app_browser_key), true);
  }

  @Override
  public void rateInAppPLay() {
  }

  @Override
  public void showEasterEgg() {
  }

  @Override
  public void followOnGithub() {
    if (mIsInAppBrowser) {
      CustomTabActivityHelper.openCustomTab(mActivity, mCustomTabsIntent.build()
          , Uri.parse(mActivity.getString(R.string.github_summary))
          , new WebViewFallback() {
            @Override
            public void openUri(Activity activity, Uri uri) {
              super.openUri(activity, uri);
            }
          });

    } else {
      openUrlLeaveApp(mActivity.getString(R.string.github_summary));
    }
  }

  private void openUrlLeaveApp(String url) {
    try {
      mActivity.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
    } catch (android.content.ActivityNotFoundException ex) {
      getView().showBrowserNotFoundError();
    }
  }

  @Override
  public void followOnJianshu() {
    if (mIsInAppBrowser) {
      CustomTabActivityHelper.openCustomTab(mActivity, mCustomTabsIntent.build()
          , Uri.parse(mActivity.getString(R.string.jianshu_summary))
          , new WebViewFallback() {
            @Override
            public void openUri(Activity activity, Uri uri) {
              super.openUri(activity, uri);
            }
          });

    } else {
      openUrlLeaveApp(mActivity.getString(R.string.jianshu_summary));
    }
  }

  @Override
  public void followOnQQ() {
  }

  @Override
  public void feedback() {
    try {
      Uri uri = Uri.parse(mActivity.getString(R.string.mail_to));
      Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
      intent.putExtra(Intent.EXTRA_SUBJECT, mActivity.getString(R.string.mail_topic));
      intent.putExtra(Intent.EXTRA_TEXT,
          mActivity.getString(R.string.device_model) + Build.MODEL + "\n"
              + mActivity.getString(R.string.sdk_version) + Build.VERSION.RELEASE + "\n"
              + mActivity.getString(R.string.version_title));
      mActivity.startActivity(intent);
    } catch (android.content.ActivityNotFoundException ex) {
      getView().showFeedbackError();
    }
  }

  @Override
  public void donate() {
    new AlertDialog.Builder(mActivity)
        .setTitle(mActivity.getString(R.string.donate))
        .setMessage(mActivity.getString(R.string.donate_message))
        .setPositiveButton(mActivity.getString(R.string.confirm), new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            ClipboardManager manager = (ClipboardManager) mActivity.getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("text", mActivity.getString(R.string.donate_account));
            manager.setPrimaryClip(clipData);

            getView().showDonateToast();
          }
        })
        .show();
  }

  @Override
  public void openLicense() {
    Intent intent = new Intent(mActivity, WebViewActivity.class);
    intent.putExtra(WebViewActivity.INTENT_KEY_TITLE, "License");
    intent.putExtra(WebViewActivity.INTENT_KEY_URL, "file:///android_asset/license.html");
    mActivity.startActivity(intent);
  }
}
