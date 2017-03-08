package wb.app.seek.widgets.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by W.b on 2017/3/8.
 */
public class FixedSpeedScroller extends Scroller {

  private int mDuration = 1200;

  public FixedSpeedScroller(Context context) {
    super(context);
  }

  public FixedSpeedScroller(Context context, Interpolator interpolator) {
    super(context, interpolator);
  }

  @SuppressLint("NewApi")
  public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
    super(context, interpolator, flywheel);
  }


  @Override
  public void startScroll(int startX, int startY, int dx, int dy, int duration) {
    super.startScroll(startX, startY, dx, dy, mDuration);
  }

  @Override
  public void startScroll(int startX, int startY, int dx, int dy) {
    super.startScroll(startX, startY, dx, dy, mDuration);
  }
}
