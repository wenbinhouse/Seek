package wb.app.seek.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by W.b on 2017/2/10.
 */
public class DateTimeUtils {

  private static String MIN_DATE = "20130520";
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

  /**
   * 验证日期是否有效
   */
  public static boolean isValid(String date) {
    long targetDate = string2Millis(date, DATE_FORMAT);
    long minDate = string2Millis(MIN_DATE, DATE_FORMAT);
    return targetDate >= minDate ? true : false;
  }

  /**
   * 将时间戳转为时间字符串
   */
  private static String timeMillis2String(long timeMillis) {
    return DATE_FORMAT.format(new Date(timeMillis));
  }

  public static String getCurrentDay() {
    return timeMillis2String(Calendar.getInstance().getTimeInMillis());
  }

  public static String formatDate(int value) {
    String str;
    if (value < 10) {
      str = String.format("%s%d", "0", value);
    } else {
      str = String.valueOf(value);
    }
    return str;
  }

  /**
   * 将时间字符串转为时间戳
   */
  private static long string2Millis(String time, SimpleDateFormat format) {
    try {
      return format.parse(time).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return -1;
  }
}
