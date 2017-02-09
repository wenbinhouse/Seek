package wb.app.seek.common.utils.mlog;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * 日志打印
 * <p>
 * Created by W.b on 2017/2/7.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class MLogPrinter {

  // 限制每条 Log 最大输出
  public static final int CHUNK_SIZE = 3200;
  // 换行符
  public static String LINE_SEPARATOR = lineSeparator();

  /**
   * @param level   级别
   * @param tag     标签
   * @param message 消息
   */
  public static void println(int level, String tag, String message) {
    if (message.length() <= CHUNK_SIZE) {
      printChunk(level, tag, message);
      return;
    }

    int msgLength = message.length();
    int start = 0;
    int end = start + CHUNK_SIZE;
    while (start < msgLength) {
      printChunk(level, tag, message.substring(start, end));

      start = end;
      end = Math.min(start + CHUNK_SIZE, msgLength);
    }
  }

  /**
   * 调用系统的日志打印
   *
   * @param level   级别
   * @param tag     标签
   * @param message 消息
   */
  private static void printChunk(int level, String tag, String message) {
    android.util.Log.println(level, tag, message);
  }

  /**
   * 获取系统换行符
   */
  @TargetApi(Build.VERSION_CODES.KITKAT)
  private static String lineSeparator() {
    try {
      Class.forName("android.os.Build");
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        return System.lineSeparator();
      }
    } catch (Exception ignored) {
    }
    return "\n";
  }
}
