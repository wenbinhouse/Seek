package wb.app.seek.modules.about;

/**
 * Created by W.b on 2017/2/15.
 */
public class AboutContract {

  interface View {

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
