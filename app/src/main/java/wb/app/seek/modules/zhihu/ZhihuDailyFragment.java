package wb.app.seek.modules.zhihu;

import android.animation.Animator;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import wb.app.seek.R;
import wb.app.seek.common.base.mvp.MvpFragment;
import wb.app.seek.common.rxbus.RxBus;
import wb.app.seek.common.rxbus.RxEvent;
import wb.app.seek.common.rxbus.RxEventType;
import wb.app.seek.common.widgets.recyclerview.OnRecyclerViewScrollListener;
import wb.app.seek.model.ZhihuDailyNews;
import wb.app.seek.utils.DateTimeUtils;

import static wb.app.seek.modules.zhihu.ZhihuDailyDetailActivity.INTENT_KEY_STORY_ID;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyFragment extends MvpFragment<ZhihuDailyPresenter> implements ZhihuDailyContract.View, SwipeRefreshLayout.OnRefreshListener {

  @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
  @BindView(R.id.refresh_layout) SwipeRefreshLayout mRefreshLayout;
  @BindView(R.id.rocket_fab) FloatingActionButton mRocketFab;
  private ZhihuDailyAdapter mZhihuListAdapter;
  private Subscription mSubscription;

  public static ZhihuDailyFragment newInstance() {
    return new ZhihuDailyFragment();
  }

  @Override
  protected ZhihuDailyPresenter createPresenter() {
    return new ZhihuDailyPresenter();
  }

  @Override
  protected int getContentViewId() {
    return R.layout.fragment_zhihu;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (!mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }
  }

  @Override
  protected void initComponents(View view) {
    initRecyclerView();

    mRefreshLayout.setOnRefreshListener(this);
    mRefreshLayout.post(new Runnable() {
      @Override
      public void run() {
        onRefresh();
      }
    });

    mSubscription = RxBus.getInstance().toObservable(RxEvent.class)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<RxEvent>() {
          @Override
          public void call(RxEvent rxEvent) {
            if (rxEvent.getType() == RxEventType.SCROLL_TO_TOP) {
              smoothScrollTop();
              getPresenter().refreshNews(rxEvent.getMessage());
            }
          }
        });
  }

  private void initRecyclerView() {
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    mZhihuListAdapter = new ZhihuDailyAdapter();
    mZhihuListAdapter.setOnItemClickListener(new ZhihuDailyAdapter.OnItemClickListener() {

      @Override
      public void onClick(int id, View view) {
//        Intent intent = new Intent(getActivity(), ZhihuDailyDetailActivity.class);
//        intent.putExtra("storyId", id);
//        getActivity().startActivity(intent);

        // Lollipop 以上实现 Transition
        Intent intent = new Intent(getActivity(), ZhihuDailyDetailActivity.class);
        intent.putExtra(INTENT_KEY_STORY_ID, id);
        ActivityOptionsCompat options
            = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.transition_name_cover_image));
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        getActivity().overridePendingTransition(0, 0);
      }
    });
    mRecyclerView.setAdapter(mZhihuListAdapter);
    mRecyclerView.addOnScrollListener(onScrollListener);
  }

  private RecyclerView.OnScrollListener onScrollListener = new OnRecyclerViewScrollListener() {

    @Override
    protected void onRefresh(boolean isCanRefresh) {
      mRefreshLayout.setEnabled(isCanRefresh ? true : false);
    }

    @Override
    protected void onLoadMore() {
      getPresenter().loadMoreNews();
    }

    @Override
    public void showRocket() {
      super.showRocket();

      mRocketFab.show();
    }

    @Override
    public void hideRocket() {
      super.hideRocket();

      mRocketFab.hide();
    }
  };

  @Override
  public void showLoading() {
    mRefreshLayout.setRefreshing(true);
  }

  @Override
  public void hideLoading() {
    if (mRefreshLayout.isRefreshing()) {
      mRefreshLayout.setRefreshing(false);
    }
  }

  @Override
  public void showError(String msg, String exception) {
    super.showError(msg, exception);
  }

  @Override
  public void showNews(ZhihuDailyNews dailyNews) {
    mZhihuListAdapter.showNews(dailyNews);
  }

  @Override
  public void showMoreNews(ZhihuDailyNews dailyNews) {
    mZhihuListAdapter.showMoreNews(dailyNews);
  }

  @Override
  public void showNoMoreNews() {
    mZhihuListAdapter.showNoMoreNews();
  }

  @Override
  public void onRefresh() {
    getPresenter().refreshNews(DateTimeUtils.getCurrentDay());
  }

  @OnClick({R.id.rocket_fab})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.rocket_fab:
        // 快速滚到到顶部
        smoothScrollTop();
        Animator anim =
          ViewAnimationUtils.createCircularReveal(mRocketFab, 200, 200, 0, 50);

        anim.start();
        break;
    }
  }

  private void smoothScrollTop() {
    mRecyclerView.scrollToPosition(0);
//    mRecyclerView.smoothScrollToPosition(0);
  }
}
