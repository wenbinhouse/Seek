package wb.app.seek.common.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import wb.app.seek.common.http.AppService;
import wb.app.seek.common.http.retrofit.AppClient;

/**
 * Created by W.b on 2016/11/29.
 */
public abstract class BaseActivity extends ToolbarActivity {

  private String TAG = getClass().getSimpleName();

  private ProgressDialog mProgressDialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (isContentViewWithToolbar()) {
      setContentView(getContentViewId());
    } else {
      setContentViewNoToolbar(getContentViewId());
    }
    ButterKnife.bind(this);
  }

  protected abstract int getContentViewId();

  protected boolean isContentViewWithToolbar() {
    return true;
  }

  public SeekHelper getHelper() {
    return ((BaseApplication) getApplication()).getHelper();
  }

  public AppService getService() {
    return AppClient.getInstance().getService();
  }

  protected void startActivity(Class<? extends BaseActivity> clz) {
    startActivity(new Intent(this, clz));
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
