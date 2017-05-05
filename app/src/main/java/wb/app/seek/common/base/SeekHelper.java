package wb.app.seek.common.base;

import android.util.DisplayMetrics;

import wb.app.seek.common.utils.AppUtils;
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
}
