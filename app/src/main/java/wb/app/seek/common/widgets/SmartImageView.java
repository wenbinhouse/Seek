package wb.app.seek.common.widgets;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

/**
 * Created by W.b on 2017/1/10.
 */
public class SmartImageView extends SimpleDraweeView {

  public SmartImageView(Context context, GenericDraweeHierarchy hierarchy) {
    super(context, hierarchy);
    init();
  }

  public SmartImageView(Context context) {
    super(context);
    init();
  }

  public SmartImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public SmartImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  public SmartImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  private void init() {

  }

  /**
   * 加载资源图片
   */
  public void setImageId(int drawableId) {
    setImageURI(Uri.parse("res:///" + drawableId));
  }

  /**
   * 加载网络图片
   */
  public void setImageUrl(String url) {
    setImageURI(Uri.parse(url));
  }

  /**
   * 加载SD卡图片
   */
  public void setImageUrlSD(String path) {
    setImageURI(Uri.fromFile(new File(path)));
  }

  /**
   * 判断Url是否可用
   */
  public static boolean checkUrl(String url) {
    if (!TextUtils.isEmpty(url)) {
      Uri uri = Uri.parse(url);
      if (UriUtil.isNetworkUri(uri)) {
        return true;
      } else if (UriUtil.isLocalFileUri(uri)) {
        return true;
      } else if (UriUtil.isLocalContentUri(uri)) {
        return true;
      } else if (UriUtil.isLocalAssetUri(uri)) {
        return true;
      } else return UriUtil.isLocalResourceUri(uri);
    }
    return false;
  }
}
