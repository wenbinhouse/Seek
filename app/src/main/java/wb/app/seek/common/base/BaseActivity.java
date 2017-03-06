package wb.app.seek.common.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by W.b on 2016/11/29.
 */
public abstract class BaseActivity extends ToolbarActivity {

  private String TAG = getClass().getSimpleName();

  private ProgressDialog mProgressDialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    int layoutId = getContentViewId();
    if (isContentViewWithToolbar()) {
      super.setContentView(layoutId);
    } else {
      setContentViewNoToolbar(layoutId);
    }

    ButterKnife.bind(this);

    initComponents();
  }

  protected abstract int getContentViewId();

  protected abstract void initComponents();

  protected boolean isContentViewWithToolbar() {
    return true;
  }

  public SeekHelper getHelper() {
    return ((BaseApplication) getApplication()).getHelper();
  }

  protected void startActivity(Class<? extends BaseActivity> clz) {
    startActivity(new Intent(this, clz));
  }
}
