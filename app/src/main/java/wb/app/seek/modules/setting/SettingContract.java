package wb.app.seek.modules.setting;

import android.preference.Preference;

/**
 * Created by W.b on 2017/2/16.
 */
public class SettingContract {

  interface View {

  }

  interface Presenter {
    void setInAppBrowser(Preference preference);

    void setNightMode(Preference preference);
  }
}
