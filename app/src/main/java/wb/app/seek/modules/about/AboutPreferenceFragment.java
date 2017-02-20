package wb.app.seek.modules.about;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.Toast;

import wb.app.seek.R;

/**
 * Created by W.b on 2017/2/16.
 */
public class AboutPreferenceFragment extends PreferenceFragment implements AboutContract.View {

  public static AboutPreferenceFragment newInstance() {
    return new AboutPreferenceFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    addPreferencesFromResource(R.xml.preference_about);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    final AboutPresenter presenter = new AboutPresenter(getActivity(), this);

    findPreference("rate").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
      @Override
      public boolean onPreferenceClick(Preference preference) {
        presenter.rateInAppPLay();
        return false;
      }
    });

    findPreference("author").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
      @Override
      public boolean onPreferenceClick(Preference preference) {
        presenter.showEasterEgg();
        return false;
      }
    });

    findPreference("github").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
      @Override
      public boolean onPreferenceClick(Preference preference) {
        presenter.followOnGithub();
        return false;
      }
    });

    findPreference("jianshu").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
      @Override
      public boolean onPreferenceClick(Preference preference) {
        presenter.followOnJianshu();
        return false;
      }
    });

//    findPreference("qq").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//      @Override
//      public boolean onPreferenceClick(Preference preference) {
//        presenter.followOnQQ();
//        return false;
//      }
//    });

//    findPreference("feedback").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//      @Override
//      public boolean onPreferenceClick(Preference preference) {
//        presenter.feedback();
//        return false;
//      }
//    });

    findPreference("donate").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
      @Override
      public boolean onPreferenceClick(Preference preference) {
        presenter.donate();
        return false;
      }
    });

    findPreference("license").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
      @Override
      public boolean onPreferenceClick(Preference preference) {
        presenter.openLicense();
        return false;
      }
    });
  }

  @Override
  public void showRateError() {
  }

  @Override
  public void showFeedbackError() {

  }

  @Override
  public void showBrowserNotFoundError() {

  }

  @Override
  public void showDonateToast() {
    Toast.makeText(getActivity(), "复制成功，请进入支付宝完成捐赠", Toast.LENGTH_SHORT).show();
  }
}
