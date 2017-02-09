package wb.app.seek.common.utils;

import android.content.Context;

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
}
