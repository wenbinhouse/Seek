package wb.app.seek.modules.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseActivity;
import wb.app.seek.common.widgets.SmartImageView;

public class MainActivity extends BaseActivity {

  @BindView(R.id.main_toolbar) Toolbar mMainToolbar;
  @BindView(R.id.drawer_main_layout) DrawerLayout mDrawerLayout;
  @BindView(R.id.main_viewpager) ViewPager mMainViewpager;
  @BindView(R.id.main_tab_layout) TabLayout mMainTabLayout;
  @BindView(R.id.user_avatar_img) SmartImageView mUserAvatarImg;
  @BindView(R.id.user_name_tv) TextView mUserNameTv;
  @BindView(R.id.user_description_tv) TextView mUserDescriptionTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initToolbar();

    initViews();
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

  @OnClick({R.id.user_avatar_img, R.id.drawer_layout, R.id.publish_timeline_fab})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.user_avatar_img:
        break;

      case R.id.drawer_layout:
        break;
    }
  }
}
