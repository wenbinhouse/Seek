package wb.app.seek.common.base;

import android.util.DisplayMetrics;

import wb.app.seek.common.utils.AppUtils;
import wb.app.seek.common.utils.SPKey;
import wb.app.seek.common.utils.SPUtils;
import wb.app.seek.common.utils.ScreenUtils;

/**
 * Created by W.b on 2017/1/9.
 */
public class SeekHelper {

  private static SeekHelper mHelper = new SeekHelper();
  private final SPUtils mSpUtils;
  private final DisplayMetrics mDisplayMetrics;

  public SeekHelper() {
    BaseApplication context = BaseApplication.getInstance();

    //SharedPreferences
    mSpUtils = new SPUtils(context, AppUtils.getAppPackageName(context));

    //屏幕尺寸
    mDisplayMetrics = ScreenUtils.getScreenInfo(context);
  }

  public static SeekHelper getInstance() {
    if (mHelper == null) {
      mHelper = new SeekHelper();
    }

    return mHelper;
  }

  public int getScreenWidth() {
    return mDisplayMetrics.widthPixels;
  }

  public int getScreenHeight() {
    return mDisplayMetrics.heightPixels;
  }

  public SPUtils getSpUtils() {
    return mSpUtils;
  }

  public void setAccessToken(String accessToken) {
    mSpUtils.putString(SPKey.ACCESS_TOKEN, accessToken);
  }

  public String getAccessToken() {
    return mSpUtils.getString(SPKey.ACCESS_TOKEN);
  }

  public void setRefreshToken(String refreshToken) {
    mSpUtils.putString(SPKey.REFRESH_TOKEN, refreshToken);
  }

  public String getRefreshToken() {
    return mSpUtils.getString(SPKey.REFRESH_TOKEN);
  }

  public void setUid(String uid) {
    mSpUtils.putString(SPKey.UID, uid);
  }

  public String getUid() {
    return mSpUtils.getString(SPKey.UID);
  }

  public void setTimelineSinceId(String id) {
    mSpUtils.putString(SPKey.FIRST_TIMELINE_ID, id);
  }

  public String getTimelineSinceId() {
    return mSpUtils.getString(SPKey.FIRST_TIMELINE_ID, "0");
  }

  public void setTimelineMaxId(String id) {
    mSpUtils.putString(SPKey.LAST_TIMELINE_ID, id);
  }

  public String getTimelineMaxId() {
    return mSpUtils.getString(SPKey.LAST_TIMELINE_ID, "0");
  }
}
