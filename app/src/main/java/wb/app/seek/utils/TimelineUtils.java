package wb.app.seek.utils;

/**
 * Created by W.b on 2017/1/18.
 */
public class TimelineUtils {

  /**
   * 计算多图分享的间隔宽度
   */
  public static int getMultiTimelineSpaceFixedLength(int screenWidth) {
    if (screenWidth < 720) {
      return 6;
    } else if (screenWidth < 1080) {
      return 8;
    } else {
      return 10;
    }
  }
}
