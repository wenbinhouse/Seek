package wb.app.seek.modules.gank;

import android.support.v4.app.Fragment;
import android.view.View;

import wb.app.seek.R;
import wb.app.seek.common.base.mvp.MvpFragment;

/**
 * Created by W.b on 2017/2/17.
 */
public class GankFragment extends MvpFragment<GankPresenter> implements GankContract.View {

  public static Fragment newInstance() {
    return new GankFragment();
  }

  @Override
  protected GankPresenter createPresenter() {
    return new GankPresenter();
  }

  @Override
  protected int getContentViewId() {
    return R.layout.fragment_zhihu;
  }

  @Override
  protected void initComponents(View view) {

  }
}
