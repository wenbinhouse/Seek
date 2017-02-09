package wb.app.seek.view.widgets;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;

import java.util.List;

import wb.app.seek.common.widgets.LineBreakLayout;

/**
 * Created by W.b on 2017/1/11.
 */
public class SharePhotoView extends LineBreakLayout {

  public SharePhotoView(Context context) {
    super(context);
  }

  public SharePhotoView(Context context, int horizontalSpacing, int verticalSpacing) {
    super(context, horizontalSpacing, verticalSpacing);
  }

  public SharePhotoView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setTimeline(String timeline) {
  }

  public void setImages(List<Image> images) {
  }
}
