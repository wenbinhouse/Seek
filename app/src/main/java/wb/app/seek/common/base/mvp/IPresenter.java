package wb.app.seek.common.base.mvp;

/**
 * Created by W.b on 2017/2/15.
 */
public interface IPresenter<V extends IView> {

  void attachView(V view);

  void detachView();
}
