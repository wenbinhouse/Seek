package wb.app.seek.modules.setting.presenter;

import wb.app.seek.common.base.mvp.IView;

/**
 * Created by W.b on 2017/2/16.
 */
public class SettingContract {

    public interface View extends IView {

    }

    public interface Presenter {
        void initView();

        void setInAppBrowser();

        void setNightMode();
    }
}
