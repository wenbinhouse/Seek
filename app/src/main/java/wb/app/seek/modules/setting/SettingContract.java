package wb.app.seek.modules.setting;

import wb.app.seek.common.base.mvp.IView;

/**
 * Created by W.b on 2017/2/16.
 */
public class SettingContract {

  interface View extends IView {

  }

  interface Presenter {
    void initView();

    void setInAppBrowser();

    void setNightMode();
  }
}
