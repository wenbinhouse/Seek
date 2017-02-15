package wb.app.seek.common.base.mvp;

/**
 * Created by W.b on 2016/11/29.
 */
public interface IView {

  void showLoading();

  void hideLoading();

  void showError(String msg, String exception);
}
