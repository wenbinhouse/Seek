package wb.app.seek.common.base.mvp;

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
}
