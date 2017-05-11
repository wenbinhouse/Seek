package wb.app.seek.common.base.list;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import wb.app.seek.R;
import wb.app.seek.common.base.mvp.BasePresenter;
import wb.app.seek.common.base.mvp.MvpFragment;
import wb.app.seek.widgets.LoadingFooterView;
import wb.app.seek.widgets.recyclerview.BaseRecyclerAdapter;

/**
 * 刷新基类
 * SwipeRefreshLayout + RecyclerView
 * <p>
 * Created by W.b on 04/05/2017.
 */
public abstract class BaseRefreshListFragment<T, P extends BasePresenter> extends MvpFragment<P> {

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swi_refresh_layout) SwipeRefreshLayout mSwiRefreshLayout;

    private List<T> mData = new ArrayList<>();
    private BaseRecyclerAdapter mAdapter;
    private LoadingFooterView mFooterView;
    private boolean mIsNoMore;// 是否还有更多
    private boolean mIsAutoQueryMore = true;// 默认开启自动加载，当数据不满一屏幕时使用

    /**
     * 布局管理器
     */
    protected abstract RecyclerView.LayoutManager getLayoutManager();

    /**
     * 布局管理器
     */
    protected abstract BaseRecyclerAdapter initAdapter();

    /**
     * 下拉刷新
     */
    protected abstract void query();

    /**
     * 加载更多
     */
    protected abstract void queryMore();

    /**
     * item 点击事件
     */
    private void onItemCommonClick(T data) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_refresh_list;
    }

    @Override
    protected void initComponents(View view) {
        mFooterView = new LoadingFooterView(getContext());

        initSwipeRefreshLayout();

        initRecyclerView();

        query();
    }

    /**
     * 初始化 SwipeRefreshLayout
     */
    private void initSwipeRefreshLayout() {
        mSwiRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query();
            }
        });

        //下拉刷新控件的配色
        mSwiRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
    }

    /**
     * 开始刷新动画
     */
    private void startRefreshAnim() {
        mSwiRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwiRefreshLayout.setRefreshing(true);
            }
        });
    }

    /**
     * 结束刷新动画
     */
    private void stopRefreshAnim() {
        mSwiRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwiRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    /**
     * 初始化 RecyclerView
     */
    private void initRecyclerView() {
        mAdapter = initAdapter();
        mAdapter.setOnItemCommonClickListener(new BaseRecyclerAdapter.OnItemCommonClickListener() {
            @Override
            public void onItemClick(int position) {
                onItemCommonClick(mData.get(position));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.addOnScrollListener(onScrollListener);

        mAdapter.addFootView(mFooterView.getView());
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int lastVisibleItemPos = findLastVisibleItemPosition(layoutManager);
            int itemCount = layoutManager.getItemCount();
            int childCount = layoutManager.getChildCount();

            // RecyclerView 在顶部
            boolean isTop = recyclerView.canScrollVertically(-1);
            // RecyclerView 在底部
            boolean isBottom = recyclerView.canScrollVertically(1);

            // 手动下拉刷新，开启自动加载，否则有可能出现加载不满一屏幕的情况
            if (!isTop) {
                mIsAutoQueryMore = true;
            }

            // 还有更多数据、RecyclerView 在底部、空闲状态，加载更多
            if (!mIsNoMore && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPos + 1 == itemCount) {
                mFooterView.setState(LoadingFooterView.STATE_LOADING);
                queryMore();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int lastVisibleItemPos = findLastVisibleItemPosition(layoutManager);
            int itemCount = layoutManager.getItemCount();
            int childCount = layoutManager.getChildCount();

            // 数据不满一屏幕时，自动加载更多直到满屏
            if (lastVisibleItemPos == 0) {
                // 加了 FooterView 第一次进入界面 lastVisibleItemPos = 0，做拦截
            } else if (mIsAutoQueryMore && lastVisibleItemPos + 1 == itemCount) {
                // 可以自动加载更多，并且当前最后可见 item 在屏幕中
                queryMore();
            } else if (mIsAutoQueryMore) {
                // 自动加载已经满屏之后关闭自动加载
                mIsAutoQueryMore = false;
            }
        }
    };

    /**
     * 找最后一个可见 item 位置
     */
    private int findLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        int layoutManagerType;
        int[] lastPositions = null;// StaggeredGridLayoutManager 最后一个位置
        int lastVisibleItemPosition = 0;// 最后一个可见 item 的位置

        if (layoutManager instanceof LinearLayoutManager) {
            layoutManagerType = 0;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            layoutManagerType = 1;
        } else {
            throw new RuntimeException(
                    "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
        }

        switch (layoutManagerType) {
            case 0:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case 1:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
                break;
        }

        return lastVisibleItemPosition;
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public void showLoading() {
        mIsNoMore = false;
        startRefreshAnim();
    }

    @Override
    public void hideLoading() {
        stopRefreshAnim();
        mFooterView.setState(LoadingFooterView.STATE_IDLE);
    }

    @Override
    public void showNetErrorView() {
        super.showNetErrorView();
    }

    @Override
    public void showEmptyView() {
        super.showEmptyView();
    }

    @Override
    public void showNoMore() {
        mIsNoMore = true;
        mFooterView.setState(LoadingFooterView.STATE_END);
    }

    /**
     * 填充数据
     */
    protected void fillData(boolean isMore, List<T> data) {
        if (isMore) {
            mAdapter.addMoreData(data.subList(0, 3));
        } else {
            mAdapter.addData(data.subList(0, 3));
        }
    }

    /**
     * 快速滑到顶部
     */
    protected void smoothScrollTop() {
        mRecyclerView.scrollToPosition(0);
//    mRecyclerView.smoothScrollToPosition(0);
    }
}
