package wb.app.seek.modules;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseRefreshListFragment;

/**
 * Created by W.b on 2017/1/9.
 */
public class RecommendFragment extends BaseRefreshListFragment<RecommendPresenter> {

  @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
  @BindView(R.id.swi_layout) SwipeRefreshLayout mSwiLayout;

  @Override
  protected RecommendPresenter createPresenter() {
    return new RecommendPresenter();
  }

  @Override
  protected void query() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // TODO: inflate a fragment view
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    ButterKnife.bind(this, rootView);

    return rootView;
  }
}
