package wb.app.seek.modules.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import wb.app.seek.R;
import wb.app.seek.common.base.mvp.MvpActivity;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyDetailActivity extends MvpActivity<ZhihuDailyDetailPresenter> implements ZhihuDailyDetailContract.View, SwipeRefreshLayout.OnRefreshListener {

  @BindView(R.id.web_view) WebView mWebView;
  @BindView(R.id.cover_img) ImageView mCoverImg;
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.toolbar_layout) CollapsingToolbarLayout mToolbarLayout;
  @BindView(R.id.appbar_layout) AppBarLayout mAppbarLayout;
  @BindView(R.id.scroll_view) NestedScrollView mScrollView;
  @BindView(R.id.refresh_layout) SwipeRefreshLayout mRefreshLayout;
  @BindView(R.id.coordinator_layout) CoordinatorLayout mCoordinatorLayout;
  private int mStoryId;

  @Override
  protected ZhihuDailyDetailPresenter createPresenter() {
    return new ZhihuDailyDetailPresenter();
  }

  @Override
  protected int getContentViewId() {
    return R.layout.activity_zhihu_daily_detail;
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mStoryId = getIntent().getIntExtra("storyId", -1);

    initWebView();

    mRefreshLayout.setOnRefreshListener(this);
    mRefreshLayout.post(new Runnable() {
      @Override
      public void run() {
        onRefresh();
      }
    });
  }

  private void initWebView() {
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
    //显示网页图片
    mWebView.getSettings().setBlockNetworkImage(false);

    mWebView.getSettings().setUseWideViewPort(true);
    mWebView.getSettings().setLoadWithOverviewMode(true);

    mWebView.setWebChromeClient(new WebChromeClient());
    mWebView.setWebViewClient(new WebViewClient());
  }

  @Override
  public void showLoading() {
    mRefreshLayout.setRefreshing(true);
  }

  @Override
  public void hideLoading() {
    mRefreshLayout.setRefreshing(false);
  }

  @Override
  public void showError(String msg, String exception) {
    super.showError(msg, exception);
  }

  @Override
  public void showCoverImg(String url) {
    Glide.with(this)
        .load(url)
        .asBitmap()
        .centerCrop()
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(mCoverImg);
  }

  @Override
  public void showTitle(String title) {
    mToolbarLayout.setTitle(title);
  }

  @Override
  public void showWeb(String body) {
    mWebView.loadDataWithBaseURL("x-data://base", body, "text/html", "utf-8", null);
  }

  @Override
  public void onRefresh() {
    getPresenter().getDetail(mStoryId);
  }
}
