package wb.app.seek.common.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wb.app.seek.common.base.BaseActivity;
import wb.app.seek.common.base.BaseFragment;

/**
 * Created by W.b on 2016/12/29.
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment implements IView {

    private P mPresenter;

    protected abstract P createPresenter();

    protected P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //-------------------- 公共的界面回调
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {
        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).showToastShort(msg);
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
