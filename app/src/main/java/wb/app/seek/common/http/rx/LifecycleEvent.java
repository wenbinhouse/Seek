package wb.app.seek.common.http.rx;

/**
 * Created by W.b on 2016/12/28.
 */
public enum LifecycleEvent {

  ATTACH_VIEW,

  /**
   * Activity Events
   */
  CREATE,
  START,
  RESUME,
  PAUSE,
  STOP,
  DESTROY,

  /**
   * Fragment Events
   */
  ATTACH,
  CREATE_VIEW,
  DESTROY_VIEW,
  DETACH

}
