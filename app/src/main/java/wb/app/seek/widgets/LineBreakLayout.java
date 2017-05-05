package wb.app.seek.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import wb.app.seek.R;

/**
 * Created by W.b on 2017/1/11.
 */
public class LineBreakLayout extends ViewGroup {

  public static final int DEFAULT_SPACING = 5;

  // 水平间距
  protected int mHorizontalSpacing;
  // 垂直间距
  protected int mVerticalSpacing;

  public LineBreakLayout(Context context) {
    super(context);
  }

  public LineBreakLayout(Context context, int horizontalSpacing, int verticalSpacing) {
    super(context);
    this.mHorizontalSpacing = horizontalSpacing;
    this.mVerticalSpacing = verticalSpacing;
  }

  public LineBreakLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.LineBreakLayout);
    mHorizontalSpacing = styledAttributes.getDimensionPixelSize(R.styleable.LineBreakLayout_horizontalSpacing, DEFAULT_SPACING);
    mVerticalSpacing = styledAttributes.getDimensionPixelSize(R.styleable.LineBreakLayout_verticalSpacing, DEFAULT_SPACING);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    final int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();

    int xPos = getPaddingLeft();
    int yPos = getPaddingTop();
    int lineHeight = 0;

    for (int i = 0; i < getChildCount(); i++) {
      final View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

        final int childWidth = child.getMeasuredWidth();
        lineHeight = child.getMeasuredHeight() + mVerticalSpacing;

        if (xPos + childWidth > width) {
          xPos = getPaddingLeft();
          yPos += lineHeight;
        }

        xPos += childWidth + mHorizontalSpacing;
      }
    }

    int height = resolveSize(yPos + lineHeight, heightMeasureSpec);
    setMeasuredDimension(width, height);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    final int count = getChildCount();
    final int width = r - l;
    int xPos = getPaddingLeft();
    int yPos = getPaddingTop();

    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      if (child.getVisibility() != GONE) {
        final int childWidth = child.getMeasuredWidth();
        final int childHeight = child.getMeasuredHeight();
        if (xPos + childWidth > width) {
          xPos = getPaddingLeft();
          yPos += childHeight + mVerticalSpacing;
        }
        child.layout(xPos, yPos, xPos + childWidth, yPos + childHeight);
        xPos += childWidth + mHorizontalSpacing;
      }
    }
  }
}
