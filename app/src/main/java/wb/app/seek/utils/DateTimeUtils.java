package wb.app.seek.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by W.b on 2017/2/10.
 */
public class DateTimeUtils {

  private static String timeMillis2String(long timeMillis) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    return dateFormat.format(new Date(timeMillis));
  }

  public static String getCurrentDay() {
    return timeMillis2String(Calendar.getInstance().getTimeInMillis());
  }
}
