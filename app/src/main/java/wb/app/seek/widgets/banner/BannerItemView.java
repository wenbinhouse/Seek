package wb.app.seek.widgets.banner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.app.seek.R;
import wb.app.seek.model.ZhihuDailyStory;

/**
 * Created by W.b on 09/03/2017.
 */
public class BannerItemView extends LinearLayout {

  @BindView(R.id.imageView) ImageView mImageView;
  @BindView(R.id.textView) TextView mTextView;

  public BannerItemView(Context context) {
    this(context, null);
  }

  public BannerItemView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BannerItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    init(context);
  }

  private void init(Context context) {
    LayoutInflater.from(context).inflate(R.layout.view_banner_item, this);
    ButterKnife.bind(this);
  }

  public void setItem(ZhihuDailyStory story) {
    Glide.with(getContext())
        .load(story.getImage())
        .centerCrop()
        .crossFade()
        .into(mImageView);

    mTextView.setText(story.getTitle());
  }
}
