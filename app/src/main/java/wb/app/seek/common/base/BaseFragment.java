package wb.app.seek.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by W.b on 2017/1/10.
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

  private P mPresenter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(getContentViewId(), container, false);
    mPresenter = createPresenter();
    if (mPresenter != null) {
      mPresenter.attachView();
    }
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initComponents(view);
  }

  @Override
  public void onDestroyView() {
    if (mPresenter != null) {
      mPresenter.detachView();
    }
    super.onDestroyView();
  }

  protected abstract P createPresenter();

  public P getPresenter() {
    return mPresenter;
  }

  protected abstract int getContentViewId();

  protected abstract void initComponents(View view);
}
