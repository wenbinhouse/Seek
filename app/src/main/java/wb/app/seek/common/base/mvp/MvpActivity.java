package wb.app.seek.common.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import wb.app.seek.common.base.BaseActivity;
import wb.app.seek.common.base.mvp.impl.IView;

/**
 * Created by W.b on 2016/11/29.
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity implements IView {

    private String TAG = getClass().getSimpleName();

    public P getPresenter() {
        return mPresenter;
    }

    private P mPresenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");
    }

    //--------------- 公共的界面回调
    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy()");
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {
        showToastShort(msg);
    }

    @Override
    public void showNetErrorView() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showNoMore() {

    }
}
