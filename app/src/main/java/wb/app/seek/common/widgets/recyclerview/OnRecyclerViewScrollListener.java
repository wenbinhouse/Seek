package wb.app.seek.common.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * Created by W.b on 2017/1/11.
 */
public abstract class OnRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

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

  protected abstract void onLoad();

  protected abstract void onLoadMore();
}
