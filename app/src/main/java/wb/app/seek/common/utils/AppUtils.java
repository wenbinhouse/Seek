package wb.app.seek.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import wb.app.library.MLog;

/**
 * App相关工具类
 * <p>
 * Created by W.b on 2017/1/9.
 */
public class AppUtils {

  private AppUtils() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }

  /**
   * 获取App包名
   *
   * @param context 上下文
   * @return App包名
   */
  public static String getAppPackageName(Context context) {
    return context.getPackageName();
  }

  /**
   * 获取应用版本号
   */
  public static String getAppVersion(Context context) {
    try {
      PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
      int versionCode = packageInfo.versionCode;
      String versionName = packageInfo.versionName;
      MLog.d("versionCode = " + versionCode + ", versionName = " + versionName);
      return versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
      return "";
    }
  }
}
