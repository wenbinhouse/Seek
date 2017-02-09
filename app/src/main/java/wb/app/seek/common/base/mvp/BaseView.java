package wb.app.seek.common.base.mvp;

/**
 * Created by W.b on 2016/11/29.
 */
public interface BaseView {

  void showLoading();

  void hideLoading();

  void onFailure(String msg, String exception);
}
