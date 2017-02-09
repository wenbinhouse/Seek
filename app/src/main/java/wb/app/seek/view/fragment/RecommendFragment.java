package wb.app.seek.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseActivity;
import wb.app.seek.common.base.BaseRefreshListFragment;
import wb.app.seek.common.base.SeekHelper;
import wb.app.seek.common.utils.mlog.MLog;
import wb.app.seek.presenter.RecommendPresenter;

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
    final SeekHelper helper = ((BaseActivity) getActivity()).getHelper();
    Map<String, String> map = new HashMap<>();
    MLog.d("current status : " + mCurrentStatus);
    if (mCurrentStatus == ACTION_LOAD_MORE_REFRESH) {
      map.put("since_id", "0");
      map.put("max_id", helper.getTimelineMaxId());
    } else {
      map.put("since_id", helper.getTimelineSinceId());
      map.put("max_id", "0");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // TODO: inflate a fragment view
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    ButterKnife.bind(this, rootView);

    MLog.d("mwb", "this is log d");

    return rootView;
  }
}
