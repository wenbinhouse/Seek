package wb.app.seek.common.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
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
 * <p>
 * Created by W.b on 04/05/2017.
 */
public abstract class BaseRefreshListFragment<T, P extends BasePresenter> extends MvpFragment<P> implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.swi_refresh_layout) SwipeRefreshLayout mSwiRefreshLayout;

    private List<T> mData = new ArrayList<>();
    private BaseRecyclerAdapter mAdapter;
    private LoadingFooterView mFooterView;

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

            boolean canScrollDown = false;
            boolean canScrollUp = true;

            canScrollUp = recyclerView.canScrollVertically(1);

            // 上拉加载更多
            if (!canScrollUp) {
                mFooterView.setState(LoadingFooterView.STATE_LOADING);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            boolean canScrollDown = false;
            boolean canScrollUp = true;

            canScrollDown = recyclerView.canScrollVertically(-1);
            canScrollUp = recyclerView.canScrollVertically(1);

//            //下拉刷新
//            if (!canScrollDown)
//                onRefresh();

            // 上拉加载更多
            if (!canScrollUp) {
                queryMore();
            }
        }
    };

    @Override
    public void showLoading() {
        startRefreshAnim();
    }

    @Override
    public void hideLoading() {
        stopRefreshAnim();
        mFooterView.setState(LoadingFooterView.STATE_IDLE);
    }

    @Override
    public void showNoMore() {
        mFooterView.setState(LoadingFooterView.STATE_END);
    }

    @Override
    public void onRefresh() {
        query();
    }

    /**
     * 填充数据
     */
    protected void fillData(boolean isMore, List<T> data) {
        if (isMore) {
            mAdapter.addMoreData(data);
        } else {
            mAdapter.addData(data);
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
