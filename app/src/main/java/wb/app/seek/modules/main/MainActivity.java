package wb.app.seek.modules.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseActivity;
import wb.app.seek.modules.about.AboutActivity;
import wb.app.seek.modules.setting.SettingActivity;

public class MainActivity extends BaseActivity {

  @BindView(R.id.tool_bar) Toolbar mMainToolbar;
  @BindView(R.id.drawer_main_layout) DrawerLayout mDrawerLayout;
  @BindView(R.id.main_viewpager) ViewPager mMainViewpager;
  @BindView(R.id.main_tab_layout) TabLayout mMainTabLayout;
  @BindView(R.id.nav_view) NavigationView mNavView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initToolbar();

    setupDrawerContent();

    initViews();
  }

  private void setupDrawerContent() {
    mNavView.setCheckedItem(R.id.nav_home);

    mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.nav_home:
            break;

          case R.id.nav_setting:
            startActivity(SettingActivity.class);
            break;

          case R.id.nav_about:
            startActivity(AboutActivity.class);
            break;
        }
        mDrawerLayout.closeDrawers();
        return true;
      }
    });
  }

  @Override
  protected int getContentViewId() {
    return R.layout.activity_main;
  }

  @Override
  public boolean isContentViewWithToolbar() {
    return false;
  }

  private void initViews() {
    mMainViewpager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
    mMainTabLayout.setupWithViewPager(mMainViewpager);
  }

  private void initToolbar() {
    setSupportActionBar(mMainToolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, mDrawerLayout, mMainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    mDrawerLayout.addDrawerListener(toggle);
    toggle.syncState();
  }
}
