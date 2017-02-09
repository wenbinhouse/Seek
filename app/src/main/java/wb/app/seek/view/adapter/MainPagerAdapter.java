package wb.app.seek.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import wb.app.seek.view.fragment.RecommendFragment;

/**
 * Created by W.b on 2017/1/10.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

  public MainPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int position) {
//    if (position == 0) {
      return new RecommendFragment();
//    }
//    return new ExploreFragment();
  }

  @Override
  public int getCount() {
    return 1;
  }

  @Override
  public CharSequence getPageTitle(int position) {
//    if (position == 0) {
      return "首页";
//    }
//    return "发现";
  }
}
