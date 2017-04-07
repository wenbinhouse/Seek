package wb.app.seek.modules.about;

import wb.app.seek.common.base.mvp.IView;

/**
 * Created by W.b on 2017/2/15.
 */
public class AboutContract {

  interface View extends IView {

    void showRateError();

    void showFeedbackError();

    void showBrowserNotFoundError();

    void showDonateToast();
  }

  interface Presenter {

    void rateInAppPLay();

    void showEasterEgg();

    void followOnGithub();

    void followOnJianshu();

    void followOnQQ();

    void feedback();

    void donate();

    void openLicense();
  }
}
