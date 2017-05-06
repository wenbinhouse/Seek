package wb.app.seek.modules.zhihu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import wb.app.seek.R;
import wb.app.seek.common.base.mvp.MvpActivity;
import wb.app.seek.modules.zhihu.presenter.ZhihuDailyDetailContract;
import wb.app.seek.modules.zhihu.presenter.ZhihuDailyDetailPresenter;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyDetailActivity extends MvpActivity<ZhihuDailyDetailPresenter> implements ZhihuDailyDetailContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String INTENT_KEY_STORY_ID = "INTENT_KEY_STORY_ID";

    @BindView(R.id.web_view) WebView mWebView;
    @BindView(R.id.cover_img) ImageView mCoverImg;
    @BindView(R.id.tool_bar) Toolbar mToolbar;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.appbar_layout) AppBarLayout mAppbarLayout;
    @BindView(R.id.coordinator_layout) CoordinatorLayout mCoordinatorLayout;
    private int mStoryId;

    @Override
    protected ZhihuDailyDetailPresenter createPresenter() {
        return new ZhihuDailyDetailPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_zhihu_daily_detail;
    }

    @Override
    protected void initComponents() {
        mStoryId = getIntent().getIntExtra(INTENT_KEY_STORY_ID, -1);

        initToolbar();

        initWebView();

        getPresenter().getDetail(mStoryId);
    }

    @Override
    protected boolean isContentViewWithToolbar() {
        return false;
    }

    private void initToolbar() {
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
        setSupportActionBar(mToolbar);
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
        //加载网页图片
        mWebView.getSettings().setBlockNetworkImage(false);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                getPresenter().openComment(view, url);
                return true;
            }
        });

//    mWebView.getSettings().setUseWideViewPort(true);
//    mWebView.getSettings().setLoadWithOverviewMode(true);

//    mWebView.setWebChromeClient(new WebChromeClient());
//    mWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
    }

    @Override
    public void showCoverImg(String url) {
        Glide.with(this)
                .load(url)
                .asBitmap()
                .centerCrop()
//        .into(new SimpleTarget<Bitmap>() {
//          @Override
//          public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//            mCoverImg.setImageBitmap(resource);
//            Palette.Builder pa = Palette.from(resource);
//            pa.generate(new Palette.PaletteAsyncListener() {
//              @Override
//              public void onGenerated(Palette palette) {
//                if (palette.getLightVibrantSwatch() != null) {
//                  getSupportActionBar().setBackgroundDrawable(new ColorDrawable(palette.getLightVibrantColor(0x000000)));
//                }
//              }
//            });
//          }
//        });
                .into(mCoverImg);
    }

    @Override
    public void showTitle(String title) {
        // 设置收缩的表填颜色
        mToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.textColor));
        // 设置展开的标题颜色
        mToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.bannerTextColor));
        mToolbarLayout.setTitle(title);
    }

    @Override
    public void showWeb(String body) {
        mWebView.loadDataWithBaseURL("x-data://base", body, "text/html", "utf-8", null);
    }

    @Override
    public void showBrowserNotFoundError() {

    }

    @Override
    public void onRefresh() {
        getPresenter().getDetail(mStoryId);
    }
}
