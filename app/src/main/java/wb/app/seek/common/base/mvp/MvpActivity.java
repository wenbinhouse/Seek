//package wb.app.seek.common.base.mvp;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.util.Log;
//
//import wb.app.seek.common.base.BaseActivity;
//import wb.app.seek.common.base.BasePresenter;
//
///**
// * Created by W.b on 2016/11/29.
// */
//public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
//
//  private String TAG = getClass().getSimpleName();
//
//  protected P mPresenter;
//
//  protected abstract P createPresenter();
//
//  @Override
//  protected void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    Log.e(TAG, "onCreate()");
//    mPresenter = createPresenter();
//    if (mPresenter != null) {
//      mPresenter.attachView();
//    }
//  }
//
//  @Override
//  protected void onDestroy() {
//    Log.e(TAG, "onDestroy()");
//    if (mPresenter != null) {
//      mPresenter.detachView();
//    }
//    super.onDestroy();
//  }
//
//  public void showLoading() {
//    showProgressDialog();
//  }
//
//  public void hideLoading() {
//    hideProgressDialog();
//  }
//
//  public void onFailure(String msg, String exception) {
//
//  }
//}
