package wb.app.seek.common.rxbus;

/**
 * RxBus 事件类型，用于接收事件时区分
 * <p>
 * Created by W.b on 2017/3/6.
 */
public interface RxEventType {

    // 列表滑动到顶部
    int SCROLL_TO_TOP = 0;

    // 夜间模式切换
    int DAY_NIGHT_MODE = 1;
}
