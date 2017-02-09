package wb.app.seek.common.utils.mlog;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import wb.app.seek.BuildConfig;

/**
 * 日志工具
 * <p>
 * Created by W.b on 2017/1/22.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class MLog {

  // 打印 Json 的级别, 默认使用 Log.d() 级别
  private static final int JSON = Log.DEBUG;

  public static void d(@NonNull String message) {
    print(Log.DEBUG, null, message);
  }

  public static void d(@NonNull String tag, @NonNull String message) {
    print(Log.DEBUG, tag, message);
  }

  public static void e(@NonNull String message) {
    print(Log.ERROR, null, message);
  }

  public static void e(@NonNull String tag, @NonNull String message) {
    print(Log.ERROR, tag, message);
  }

  public static void json(@NonNull String message) {
    print(JSON, null, message);
  }

  public static void json(@NonNull String tag, @NonNull String message) {
    print(JSON, tag, message);
  }

  /**
   * 日志打印
   *
   * @param level   级别
   * @param tag     标签
   * @param message 信息
   */
  private static void print(int level, String tag, String message) {
    // release 版本不输出日志信息
    if (!BuildConfig.DEBUG) {
      return;
    }

    //获取方法的调用堆栈数组
    StackTraceElement[] elements = Thread.currentThread().getStackTrace();
    //本地方法的调用堆栈信息
    StackTraceElement element = elements[4];

    if (TextUtils.isEmpty(tag)) {
      tag = MLogFormat.getName(element.getClassName(), true);
    }

    switch (level) {
      case JSON:
        printJson(level, tag, message, element);
        break;

      default:
        printConsole(level, tag, message, element);
        break;
    }
  }

  /**
   * 日志打印输出到控制台.
   *
   * @param level   级别
   * @param tag     标签
   * @param message 信息
   * @param element 堆栈元素
   */
  private static void printConsole(int level, @NonNull String tag, @NonNull String message, @NonNull StackTraceElement element) {
    String formatMessage = MLogFormat.formatMessage(message, element);
    MLogPrinter.println(level, tag, MLogFormat.formatBorder(new String[]{formatMessage}));
  }

  /**
   * 打印 JSON 格式的日志
   *
   * @param level   级别
   * @param tag     标签
   * @param message 消息
   * @param element 堆栈元素
   */
  private static void printJson(int level, @NonNull String tag, @NonNull String message, @NonNull StackTraceElement element) {
    String formatJson = MLogFormat.formatJson(message);
    String formatMessage = MLogFormat.formatMessage(formatJson, element);
    MLogPrinter.println(level, tag, MLogFormat.formatBorder(new String[]{formatMessage}));
  }
}
