package wb.app.seek.common.rxbus;

/**
 * RxBus 事件模型
 * <p>
 * Created by W.b on 2017/3/6.
 */
public class RxEvent {

    private int type;
    private String message;

    public RxEvent(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
