package wb.app.seek.modules.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import wb.app.seek.modules.zhihu.ZhihuDailyFragment;

/**
 * Created by W.b on 2017/1/10.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

  public MainPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int position) {
    return ZhihuDailyFragment.newInstance();
  }

  @Override
  public int getCount() {
    return 1;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return "知乎日报";
  }
}
