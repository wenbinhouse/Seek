package wb.app.seek.common.utils.mlog;

import android.annotation.TargetApi;
import android.os.Build;
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
  // 限制每条 Log 最大输出
  public static final int CHUNK_SIZE = 3200;
  // 换行符
  public static String LINE_SEPARATOR = lineSeparator();

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
    println(level, tag, MLogFormat.formatBorder(new String[]{formatMessage}));
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
    println(level, tag, MLogFormat.formatBorder(new String[]{formatMessage}));
  }

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
