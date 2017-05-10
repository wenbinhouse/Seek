package wb.app.seek.common.base.mvp.impl;

/**
 * Created by W.b on 2016/11/29.
 */
public interface IView {

    /**
     * 显示加载进度条
     */
    void showLoading();

    /**
     * 隐藏加载进度条
     */
    void hideLoading();

    /**
     * 显示异常信息
     */
    void showError(String msg);

    /**
     * 显示网络异常界面
     */
    void showNetErrorView();

    /**
     * 显示数据为空界面
     */
    void showEmptyView();

    /**
     * 显示没有更多数据
     */
    void showNoMore();
}
