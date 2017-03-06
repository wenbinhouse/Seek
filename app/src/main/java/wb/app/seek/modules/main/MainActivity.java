package wb.app.seek.modules.main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.app.library.MLog;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseActivity;
import wb.app.seek.modules.about.AboutActivity;
import wb.app.seek.modules.setting.SettingActivity;
import wb.app.seek.utils.DateTimeUtils;

public class MainActivity extends BaseActivity {

  @BindView(R.id.tool_bar) Toolbar mMainToolbar;
  @BindView(R.id.drawer_main_layout) DrawerLayout mDrawerLayout;
  @BindView(R.id.main_viewpager) ViewPager mMainViewpager;
  @BindView(R.id.main_tab_layout) TabLayout mMainTabLayout;
  @BindView(R.id.nav_view) NavigationView mNavView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initToolbar();

    setupDrawerContent();

    initViews();
  }

  @Override
  public boolean isContentViewWithToolbar() {
    return false;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.main_menu_date) {
      selectedDate();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * 选择日期
   */
  private void selectedDate() {
    final Calendar calendar = Calendar.getInstance();
    int curYear = calendar.get(Calendar.YEAR);
    int curMonth = calendar.get(Calendar.MONTH);
    int curDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month += 1;
        String monthStr = DateTimeUtils.formatDate(month);
        String dayOfMonthStr = DateTimeUtils.formatDate(dayOfMonth);
        MLog.d(String.format("%1$d%2$s%3$s", year, monthStr, dayOfMonthStr));
      }
    }, curYear, curMonth, curDayOfMonth);
    datePickerDialog.show();
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
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
            break;

          case R.id.nav_about:
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            break;
        }
        mDrawerLayout.closeDrawers();
        return true;
      }
    });
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
