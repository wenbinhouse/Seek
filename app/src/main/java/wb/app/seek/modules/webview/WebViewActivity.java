package wb.app.seek.modules.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseActivity;

/**
 * Created by W.b on 2017/2/17.
 */
public class WebViewActivity extends BaseActivity {

  public static final String INTENT_KEY_URL = "INTENT_KEY_URL";
  public static final String INTENT_KEY_TITLE = "INTENT_KEY_TITLE";

  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.web_view) WebView mWebView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);
    setTitle("");

    ButterKnife.bind(this);

    String url = getIntent().getStringExtra(INTENT_KEY_URL);
    final String title = getIntent().getStringExtra(INTENT_KEY_TITLE);

    mWebView.setScrollbarFadingEnabled(true);
    //能够和js交互
    mWebView.getSettings().setJavaScriptEnabled(true);
    //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
    mWebView.getSettings().setBuiltInZoomControls(false);
    //缓存
    mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    //开启DOM storage API功能
    mWebView.getSettings().setDomStorageEnabled(true);
    //开启application Cache功能
    mWebView.getSettings().setAppCacheEnabled(false);
    //加载网页图片
    mWebView.getSettings().setBlockNetworkImage(false);

    mWebView.setWebChromeClient(new WebChromeClient() {
      @Override
      public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);

        if (TextUtils.isEmpty(title)) {
          setTitle(mWebView.getTitle());
        } else {
          setTitle(title);
        }

        if (newProgress < 100) {
          mProgressBar.setVisibility(View.VISIBLE);
          mProgressBar.setMax(100);
          mProgressBar.setProgress(newProgress);
        } else {
          mProgressBar.setVisibility(View.INVISIBLE);
        }
      }
    });
    mWebView.setWebViewClient(new WebViewClient());

    mWebView.loadUrl(url);
  }
}
