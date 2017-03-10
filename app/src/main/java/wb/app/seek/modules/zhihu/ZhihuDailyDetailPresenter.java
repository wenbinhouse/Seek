package wb.app.seek.modules.zhihu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.webkit.WebView;

import wb.app.seek.R;
import wb.app.seek.common.base.BaseApplication;
import wb.app.seek.common.base.mvp.BasePresenter;
import wb.app.seek.common.http.rx.BaseSubscriber;
import wb.app.seek.common.http.rx.ResponseTransformer;
import wb.app.seek.common.utils.SPUtils;
import wb.app.seek.model.ZhihuDailyStory;
import wb.app.seek.modules.customtabs.CustomTabActivityHelper;
import wb.app.seek.modules.customtabs.WebViewFallback;

/**
 * Created by W.b on 2017/2/10.
 */
public class ZhihuDailyDetailPresenter extends BasePresenter<ZhihuDailyDetailContract.View> implements ZhihuDailyDetailContract.Presenter {

  private final Activity mActivity;
  private final boolean mIsInAppBrowser;

  public ZhihuDailyDetailPresenter(Activity activity) {
    mActivity = activity;

    SPUtils spUtils = BaseApplication.getInstance().getHelper().getSpUtils();
    mIsInAppBrowser = spUtils.getBoolean(mActivity.getString(R.string.setting_in_app_browser_key), true);
  }

  @Override
  public void getDetail(int storyId) {
    getView().showLoading();

    getService().getZhihuNewsDetail(storyId)
        .compose(ResponseTransformer.<ZhihuDailyStory>getInstance(mLifecycleSubject))
        .subscribe(new BaseSubscriber<ZhihuDailyStory>() {
          @Override
          public void onSuccess(ZhihuDailyStory data) {
            if (!isAttach()) {
              return;
            }

            if (data != null) {
              getView().showTitle(data.getTitle());
              getView().showCoverImg(data.getImage());
              getView().showWeb(convertZhihuContent(data.getBody()));
            }
          }

          @Override
          public void onFailure(String msg, String exception) {
            if (!isAttach()) {
              return;
            }

            getView().showError(msg, exception);
          }

          @Override
          public void onFinish() {
            if (!isAttach()) {
              return;
            }
            getView().hideLoading();
          }
        });
  }

  private String convertZhihuContent(String preResult) {

    preResult = preResult.replace("<div class=\"img-place-holder\">", "");
    preResult = preResult.replace("<div class=\"headline\">", "");

    // 在api中，css的地址是以一个数组的形式给出，这里需要设置
    // in fact,in api,css addresses are given as an array
    // api中还有js的部分，这里不再解析js
    // javascript is included,but here I don't use it
    // 不再选择加载网络css，而是加载本地assets文件夹中的css
    // use the css file from local assets folder,not from network
    String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";


    // 根据主题的不同确定不同的加载内容
    // load content judging by different theme
    String theme = "<body className=\"\" onload=\"onLoaded()\">";
//    if ((context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
//        == Configuration.UI_MODE_NIGHT_YES){
//      theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
//    }

    return new StringBuilder()
        .append("<!DOCTYPE html>\n")
        .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
        .append("<head>\n")
        .append("\t<meta charset=\"utf-8\" />")
        .append(css)
        .append("\n</head>\n")
        .append(theme)
        .append(preResult)
        .append("</body></html>").toString();
  }

  public void openComment(WebView view, String url) {
    if (mIsInAppBrowser) {
      CustomTabsIntent.Builder customTabsIntent = new CustomTabsIntent.Builder();
      customTabsIntent.setToolbarColor(Color.WHITE);
      customTabsIntent.setShowTitle(true);
      CustomTabActivityHelper.openCustomTab(mActivity,
          customTabsIntent.build(),
          Uri.parse(url),
          new WebViewFallback() {
            @Override
            public void openUri(Activity activity, Uri uri) {
              super.openUri(activity, uri);
            }
          });

    } else {
      try {
        mActivity.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
      } catch (android.content.ActivityNotFoundException ex) {
        getView().showBrowserNotFoundError();
      }
    }
  }
}
