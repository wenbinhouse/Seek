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
        showRocket();
      } else {
        hideRocket();
      }
    }

    boolean canScrollDown = recyclerView.canScrollVertically(-1);
    boolean canScrollUp = recyclerView.canScrollVertically(1);

    if (!canScrollDown) {
      //下拉刷新
      onLoad();
    }

    if (!canScrollUp) {
      //上拉加载更多
      onLoadMore();
    }
  }

  public void hideRocket() {

  }

  public void showRocket() {

  }

  protected abstract void onLoad();

  protected abstract void onLoadMore();
}
