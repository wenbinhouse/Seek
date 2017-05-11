package wb.app.seek.common.http.rx;

/**
 * 生命周期事件
 * <p>
 * Created by W.b on 2016/12/28.
 */
public class LifecycleEvent {

    /**
     * Presenter Events
     */
    public enum PresenterLifecycle {

        ATTACH_VIEW,

        DETACH_VIEW
    }

    /**
     * Activity Events
     */
    public enum ActivityLifecycle {
        CREATE,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY
    }

    /**
     * Fragment Events
     */
    public enum FragmentLifecycle {
        ATTACH,
        CREATE,
        CREATE_VIEW,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY_VIEW,
        DESTROY,
        DETACH
    }
}
