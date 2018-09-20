package wb.app.seek.widgets.banner;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import wb.app.seek.R;
import wb.app.seek.modules.model.ZhihuDailyStory;
import wb.app.seek.widgets.CircleIndicator;

/**
 * Created by W.b on 2017/3/7.
 */
public class BannerView extends LinearLayout implements Runnable {

  public static final int INTERVAL = 3000;

  private ViewPager mViewPager;
  private CircleIndicator mCircleIndicator;
  private BannerAdapter mAdapter;
  private List<ZhihuDailyStory> mDailyStoryList = new ArrayList<>();
  private boolean mIsRunning;

  public BannerView(@NonNull Context context) {
    this(context, null);
  }

  public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    init(context);
  }

  private void init(Context context) {
    LayoutInflater.from(context).inflate(R.layout.view_banner, this);

    mViewPager = (ViewPager) findViewById(R.id.viewPager);
    mCircleIndicator = (CircleIndicator) findViewById(R.id.circleIndicator);

    mAdapter = new BannerAdapter(mBannerItemClickListener);
    mViewPager.setAdapter(mAdapter);

    try {
      Field mScroller = ViewPager.class.getDeclaredField("mScroller");
      mScroller.setAccessible(true);
      FixedSpeedScroller scroller = new FixedSpeedScroller(mViewPager.getContext());
      mScroller.set(mViewPager, scroller);
    } catch (Exception e) {
      e.printStackTrace();
    }

    mViewPager.setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        final int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
          stopAutoScroll();

        } else if (action != MotionEvent.ACTION_MOVE) {
          startAutoScroll();
        }
        return false;
      }
    });
  }

  public void setBanner(List<ZhihuDailyStory> dailyStoryList) {
    if (hasBanner()) {
      startAutoScroll();
      return;
    }

    if (dailyStoryList != null && dailyStoryList.size() > 0 && !mIsRunning) {
      mDailyStoryList.clear();
      mDailyStoryList.addAll(dailyStoryList);
      mAdapter.setBanner(dailyStoryList);
      mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() / 2);
      mCircleIndicator.setViewPager(mViewPager, mDailyStoryList.size());

      startAutoScroll();
    }
  }

  private boolean hasBanner() {
    return mDailyStoryList != null && mDailyStoryList.size() > 0;
  }

  /**
   * 开始自动滑动
   */
  public void startAutoScroll() {
    stopAutoScroll();
    if (mViewPager.getAdapter() != null && mViewPager.getAdapter().getCount() > 1) {
      mIsRunning = true;
      postDelayed(this, INTERVAL);
    }
  }

  public void stopAutoScroll() {
    mIsRunning = false;
    removeCallbacks(this);
  }

  @Override
  public void run() {
    if (mViewPager.getAdapter() != null) {
      if (mDailyStoryList != null && mDailyStoryList.size() > 1) {
        final int nCurrentItem = mViewPager.getCurrentItem();
        mViewPager.setCurrentItem(nCurrentItem + 1, true);
        if (nCurrentItem == (mDailyStoryList.size() - 1)) {
          mViewPager.setCurrentItem(0, true);
        }
        postDelayed(this, INTERVAL);
      }
    }
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    mIsRunning = false;
    stopAutoScroll();
  }

  private BannerAdapter.BannerItemClickListener mBannerItemClickListener = new BannerAdapter.BannerItemClickListener() {
    @Override
    public void onItemClick(ZhihuDailyStory dailyStory) {
      if (mListener != null) {
        mListener.onBannerClick(dailyStory.getId());
      }
    }
  };

  private BannerClickListener mListener;

  public void setBannerClickListener(BannerClickListener listener) {
    mListener = listener;
  }

  public interface BannerClickListener {
    void onBannerClick(int id);
  }
}
