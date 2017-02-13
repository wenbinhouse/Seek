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
import android.widget.Toast;

import butterknife.BindView;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseActivity;

public class MainActivity extends BaseActivity {

  @BindView(R.id.main_toolbar) Toolbar mMainToolbar;
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
    mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
          case R.id.nav_home:
            Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
            break;

          case R.id.nav_setting:
            Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
            break;

          case R.id.nav_about:
            Toast.makeText(MainActivity.this, "about", Toast.LENGTH_SHORT).show();
            break;
        }
        mDrawerLayout.closeDrawers();
        return true;
      }
    });
    mNavView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();
            return true;
          }
        });
  }

  @Override
  protected int getContentViewId() {
    return R.layout.activity_main;
  }

  private void initViews() {
    mMainViewpager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
    mMainTabLayout.setupWithViewPager(mMainViewpager);
  }

  private void initToolbar() {
    mMainToolbar.setTitle("");
    setSupportActionBar(mMainToolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, mDrawerLayout, mMainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    mDrawerLayout.addDrawerListener(toggle);
    toggle.syncState();
  }
}
