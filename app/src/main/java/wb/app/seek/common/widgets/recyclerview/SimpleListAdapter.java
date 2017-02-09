package wb.app.seek.common.widgets.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wb.app.seek.R;
import wb.app.seek.common.widgets.SmartImageView;
import wb.app.seek.view.widgets.SharePhotoView;

/**
 * Created by W.b on 2017/1/17.
 */
public class SimpleListAdapter extends BaseListAdapter<String> {
  @Override
  protected BaseViewHolder onCreateHolder(ViewGroup parent, int viewType) {
    return new SimpleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline, parent, false));
  }

  public class SimpleViewHolder extends BaseViewHolder<String> {

    private final SmartImageView mUserAvatarImg;
    private final TextView mUserNameTv;
    private final TextView mCreatedAtTv;
    private final TextView mContentTv;
    private final TextView mReContentTv;
    private final SharePhotoView mPhotoView;
    private final SharePhotoView mRePhotoView;
    private final View mReTimelineView;
    private final TextView mSourceTv;

    public SimpleViewHolder(View itemView) {
      super(itemView);

      mUserAvatarImg = (SmartImageView) getView(R.id.user_avatar_img);
      mUserNameTv = (TextView) getView(R.id.user_name_tv);
      mCreatedAtTv = (TextView) getView(R.id.created_at_tv);
      mContentTv = (TextView) getView(R.id.content_tv);
      mReContentTv = (TextView) getView(R.id.re_content_tv);
      mPhotoView = (SharePhotoView) getView(R.id.share_photo_view);
      mRePhotoView = (SharePhotoView) getView(R.id.re_share_photo_view);
      mReTimelineView = getView(R.id.re_timeline_layout);
      mSourceTv = (TextView) getView(R.id.source_tv);
    }

    @Override
    public void onBindView(String timeline) {
    }
  }
}
