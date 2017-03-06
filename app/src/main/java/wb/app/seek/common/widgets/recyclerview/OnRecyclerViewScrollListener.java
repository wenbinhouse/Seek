package wb.app.seek.common.widgets.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by W.b on 2017/1/11.
 */
public abstract class OnRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager instanceof LinearLayoutManager) {
      LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
      int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
      if (firstVisibleItemPosition > 10) {
        if (dy > 0)
          hideRocket();
        else
          showRocket();
      } else {
        hideRocket();
      }
    }

    boolean canScrollDown = recyclerView.canScrollVertically(-1);
    boolean canScrollUp = recyclerView.canScrollVertically(1);

    //下拉刷新
    onRefresh(!canScrollDown ? true : false);

    if (!canScrollUp) {
      //上拉加载更多
      onLoadMore();
    }
  }

  protected void onRefresh(boolean isCanRefresh) {

  }

  protected abstract void onLoadMore();

  public void hideRocket() {

  }

  public void showRocket() {

  }
}
