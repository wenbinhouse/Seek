package wb.app.seek.common.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import wb.app.seek.R;
import wb.app.seek.common.utils.mlog.MLog;
import wb.app.seek.common.widgets.recyclerview.BaseListAdapter;
import wb.app.seek.common.widgets.recyclerview.OnRecyclerViewScrollListener;
import wb.app.seek.common.widgets.recyclerview.SimpleListAdapter;

/**
 * Created by W.b on 2017/1/11.
 */
public abstract class BaseRefreshListFragment<P extends BasePresenter> extends BaseFragment<P> implements SwipeRefreshLayout.OnRefreshListener {

  private String TAG = "BaseRefreshListFragment";

  public static final int ACTION_PULL_TO_REFRESH = 1;
  public static final int ACTION_LOAD_MORE_REFRESH = 2;
  public static final int ACTION_IDLE = 0;

  protected SwipeRefreshLayout mSwiLayout;
  protected RecyclerView mRecyclerView;
  protected BaseListAdapter mListAdapter;
  protected int mCurrentStatus = ACTION_IDLE;
  private List<String> mData = new ArrayList<>();
  private RecyclerView.LayoutManager mLayoutManager;

  @Override
  protected int getContentViewId() {
    return R.layout.activity_refresh_list;
  }

  @Override
  protected void initComponents(View view) {
    mSwiLayout = (SwipeRefreshLayout) view.findViewById(R.id.swi_layout);
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

    mSwiLayout.setOnRefreshListener(this);
//    mSwiLayout.setRefreshing(true);
    mSwiLayout.post(new Runnable() {
      @Override
      public void run() {
        onRefresh();
      }
    });

    mLayoutManager = getLayoutManager();
    mRecyclerView.setLayoutManager(mLayoutManager);
    mListAdapter = new SimpleListAdapter();
    mRecyclerView.setAdapter(mListAdapter);
    mRecyclerView.addOnScrollListener(onScrollListener);
  }

  protected RecyclerView.LayoutManager getLayoutManager() {
    return new LinearLayoutManager(getActivity());
  }

  @Override
  public void onRefresh() {
    mCurrentStatus = ACTION_PULL_TO_REFRESH;
    query();
  }

  protected abstract void query();

  protected void setDataSet(List<String> data) {
    if (data != null && data.size() > 0) {
      getData().addAll(data);
      mListAdapter.initData(getData());
    }
    onRefreshCompleted();
  }

  private void notifyListDataSetChanged() {
    mListAdapter.notifyDataSetChanged();
  }

  public void onRefreshCompleted() {
    switch (mCurrentStatus) {
      case ACTION_PULL_TO_REFRESH:
        mSwiLayout.setRefreshing(false);
        break;

      case ACTION_LOAD_MORE_REFRESH:
        break;
    }
    mCurrentStatus = ACTION_IDLE;
  }

  private List<String> getData() {
    return mData;
  }

  private RecyclerView.OnScrollListener onScrollListener = new OnRecyclerViewScrollListener() {

    @Override
    protected void onLoad() {
    }

    @Override
    protected void onLoadMore() {
      if (mCurrentStatus == ACTION_IDLE) {
        mCurrentStatus = ACTION_LOAD_MORE_REFRESH;

        MLog.d("load more");

        checkIfNeedLoadMore();

        query();
      }
    }
  };

  private boolean checkIfNeedLoadMore() {
    return mData != null && mData.size() > 0;
  }
}
