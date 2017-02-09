package wb.app.seek.common.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import wb.app.seek.common.http.AppService;
import wb.app.seek.common.http.retrofit.AppClient;

/**
 * Created by W.b on 2016/11/29.
 */
public abstract class BaseActivity<P extends BasePresenter> extends ToolbarActivity {

  private String TAG = getClass().getSimpleName();

  private ProgressDialog mProgressDialog;

  private P mPresenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(getContentViewId());
    ButterKnife.bind(this);

    mPresenter = createPresenter();
    if (mPresenter != null) {
      mPresenter.attachView();
    }
  }

  @Override
  protected void onDestroy() {
    if (mPresenter != null) {
      mPresenter.detachView();
    }
    super.onDestroy();
  }

  protected abstract P createPresenter();

  protected abstract int getContentViewId();

  public P getPresenter() {
    return mPresenter;
  }

  public SeekHelper getHelper() {
    return ((BaseApplication) getApplication()).getHelper();
  }

  public AppService getService() {
    return AppClient.getInstance().getService();
  }

  protected void showProgressDialog() {
    if (mProgressDialog == null) {
      mProgressDialog = new ProgressDialog(this);
    }
    mProgressDialog.setMessage("正在加载中...");
    mProgressDialog.setCancelable(false);
    mProgressDialog.setCanceledOnTouchOutside(false);
    mProgressDialog.show();
  }

  protected void hideProgressDialog() {
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.dismiss();
    }
  }
}
