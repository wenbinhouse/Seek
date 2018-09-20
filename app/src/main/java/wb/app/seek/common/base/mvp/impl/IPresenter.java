package wb.app.seek.common.base.mvp.impl;

/**
 * Created by W.b on 2017/2/15.
 */
public interface IPresenter<V extends IView> {

    /**
     * 绑定 view
     */
    void attachView(V view);

    /**
     * 解绑 view 避免内存泄漏
     */
    void detachView();

    /**
     * 是否绑定 View
     * true - 已经绑定
     * false - 解除绑定
     */
    boolean isAttachView();
}
