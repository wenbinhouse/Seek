package wb.app.seek.modules;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

/**
 * Created by W.b on 2017/2/13.
 */
public class ScrollAwareFabBehavior extends FloatingActionButton.Behavior {

  private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

  private boolean mIsAnimatingOut = false;

  public ScrollAwareFabBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                                     final View directTargetChild, final View target, final int nestedScrollAxes) {
    return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  @Override
  public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                             final View target, final int dxConsumed, final int dyConsumed,
                             final int dxUnconsumed, final int dyUnconsumed) {
    super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE && !mIsAnimatingOut) {
      animateOut(child);

    } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
      animateIn(child);
    }
  }

  private void animateOut(final FloatingActionButton child) {
    ViewCompat.animate(child).translationY(child.getHeight() + getMarginBottom(child)).setInterpolator(INTERPOLATOR).withLayer()
        .setListener(new ViewPropertyAnimatorListener() {
          public void onAnimationStart(View view) {
            ScrollAwareFabBehavior.this.mIsAnimatingOut = true;
          }

          public void onAnimationCancel(View view) {
            ScrollAwareFabBehavior.this.mIsAnimatingOut = false;
          }

          public void onAnimationEnd(View view) {
            ScrollAwareFabBehavior.this.mIsAnimatingOut = false;
            view.setVisibility(View.GONE);
          }
        }).start();
  }

  private void animateIn(FloatingActionButton child) {
    child.setVisibility(View.VISIBLE);
    ViewCompat.animate(child).translationY(0)
        .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
        .start();
  }

  private int getMarginBottom(View v) {
    int marginBottom = 0;
    final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
      marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
    }
    return marginBottom;
  }
}
