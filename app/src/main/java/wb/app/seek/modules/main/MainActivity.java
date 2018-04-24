package wb.app.seek.modules.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import wb.app.library.MLog;
import wb.app.seek.R;
import wb.app.seek.common.base.BaseActivity;
import wb.app.seek.common.rxbus.RxEvent;
import wb.app.seek.common.rxbus.RxEventType;
import wb.app.seek.common.utils.DateTimeUtils;
import wb.app.seek.common.utils.SPKey;
import wb.app.seek.common.utils.SPUtils;
import wb.app.seek.modules.about.AboutActivity;
import wb.app.seek.modules.main.adapter.MainPagerAdapter;
import wb.app.seek.modules.setting.SettingActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tool_bar) Toolbar mMainToolbar;
    @BindView(R.id.drawer_main_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.main_viewpager) ViewPager mMainViewpager;
    @BindView(R.id.main_tab_layout) TabLayout mMainTabLayout;
    @BindView(R.id.reveal_root) RelativeLayout mRevealRoot;
    private long mLastTimeMillis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MLog.d("test commit");
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponents() {
        initToolbar();

        mMainViewpager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        mMainTabLayout.setupWithViewPager(mMainViewpager);

        // 延时切换日夜间模式
        registerEvent(new Consumer<RxEvent>() {
            @Override
            public void accept(@NonNull final RxEvent rxEvent) throws Exception {
                if (rxEvent.getType() == RxEventType.DAY_NIGHT_MODE) {
                    Observable.timer(500, TimeUnit.MILLISECONDS)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(@NonNull Long aLong) throws Exception {
                                    switchDayNightMode(rxEvent.getMessage());
                                }
                            });
                }
            }
        });
    }

    /**
     * 切换日间模式和夜间模式
     */
    private void switchDayNightMode(String message) {
        MLog.d("night mode : " + message);
        SPUtils spUtils = getHelper().getSpUtils();
        if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
                == Configuration.UI_MODE_NIGHT_YES) {
            spUtils.putInt(SPKey.UI_MODE, AppCompatDelegate.MODE_NIGHT_NO);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            spUtils.putInt(SPKey.UI_MODE, AppCompatDelegate.MODE_NIGHT_YES);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        recreate();
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
//            revealView();
            selectedDate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - mLastTimeMillis > 2000) {
                showToastShort("再按一次退出程序");
                mLastTimeMillis = currentTimeMillis;
            } else {
                super.onBackPressed();
            }
        }
    }

    private void revealView() {
        int cx = (mRevealRoot.getLeft() + mRevealRoot.getRight()) / 2;
        int cy = (mRevealRoot.getTop() + mRevealRoot.getBottom()) / 2;

        createAnimateReveal(mRevealRoot, R.color.color_reveal, cx, cy);
    }

    /**
     * 创建扩散动画
     */
    private Animator createAnimateReveal(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setBackgroundColor(ContextCompat.getColor(this, color));
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                mRevealRoot.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.transparent));
            }
        });
        anim.start();
        return anim;
    }

    /**
     * 选择日期
     */
    private void selectedDate() {
        final Calendar calendar = Calendar.getInstance();
        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.MyDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                String monthStr = DateTimeUtils.formatDate(month);
                String dayOfMonthStr = DateTimeUtils.formatDate(dayOfMonth);
                String date = String.format("%1$d%2$s%3$s", year, monthStr, dayOfMonthStr);
                // 发送事件，通知列表更新
                postEvent(new RxEvent(RxEventType.SCROLL_TO_TOP, date));
            }
        }, curYear, curMonth, curDayOfMonth);
        datePickerDialog.show();
    }

    private void initToolbar() {
        setSupportActionBar(mMainToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mMainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @OnClick({R.id.nav_home, R.id.nav_setting, R.id.nav_about, R.id.drawer_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_home:
                break;

            case R.id.nav_setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;

            case R.id.nav_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;

            case R.id.drawer_layout:
                break;
        }

        closeDrawer();
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }
}
