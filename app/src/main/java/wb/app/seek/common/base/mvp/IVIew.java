package wb.app.seek.common.base.mvp;

/**
 * Created by W.b on 2016/11/29.
 */
public interface IView {

    // TODO 显示加载进度条
    void showLoading();

    // TODO 隐藏加载进度条
    void hideLoading();

    // TODO 显示异常信息
    void showError(String msg);

    // TODO 显示网络异常界面
    void showNetErrorView();

    // TODO 显示数据为空界面
    void showEmptyView();

    // TODO 显示没有更多数据
    void showNoMore();
}
