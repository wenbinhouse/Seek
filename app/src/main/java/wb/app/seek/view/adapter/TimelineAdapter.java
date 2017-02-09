package wb.app.seek.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.app.seek.R;
import wb.app.seek.common.widgets.SmartImageView;
import wb.app.seek.common.widgets.recyclerview.BaseListAdapter;
import wb.app.seek.common.widgets.recyclerview.BaseViewHolder;
import wb.app.seek.view.widgets.SharePhotoView;

/**
 * Created by W.b on 2017/1/10.
 */
public class TimelineAdapter extends BaseListAdapter<String> {

  @Override
  protected BaseViewHolder onCreateHolder(ViewGroup parent, int viewType) {
    return new TimelineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline, parent, false));
  }

  public class TimelineViewHolder extends BaseViewHolder<String> {

    @BindView(R.id.user_avatar_img) SmartImageView mUserAvatarImg;
    @BindView(R.id.user_name_tv) TextView mUserNameTv;
    @BindView(R.id.created_at_tv) TextView mCreatedAtTv;
    @BindView(R.id.content_tv) TextView mContentTv;
    @BindView(R.id.re_content_tv) TextView mReContentTv;
    @BindView(R.id.share_photo_view) SharePhotoView mSharePhotoView;

    public TimelineViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindView(String timeline) {
    }
  }
}
//public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder> {
//
//  private List<Timeline> mTimelineList;
//
//  public void setTimelineList(List<Timeline> timelineList) {
//    mTimelineList = timelineList;
//    notifyDataSetChanged();
//  }
//
//  @Override
//  public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//    return new TimelineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline, parent, false));
//  }
//
//  @Override
//  public void onBindViewHolder(TimelineViewHolder holder, int position) {
//    Timeline timeline = mTimelineList.get(position);
//    User user = timeline.getUser();
//    Timeline reTimeline = timeline.getRetweeted_status();
//    if (user != null) {
//      holder.mUserAvatarImg.setImageUrl(user.getAvatar_large());
//      holder.mUserNameTv.setText(user.getName());
//    }
//    holder.mCreatedAtTv.setText(timeline.getCreated_at());
//    holder.mContentTv.setText(timeline.getText());
//
//    if (reTimeline != null) {
//      holder.mReContentTv.setText(reTimeline.getText());
//      holder.mSharePhotoView.setTimeline(reTimeline);
//    }
//  }
//
//  @Override
//  public int getItemCount() {
//    return mTimelineList != null ? mTimelineList.size() : 0;
//  }
//
//  public class TimelineViewHolder extends RecyclerView.ViewHolder {
//
//    @BindView(R.id.user_avatar_img) SmartImageView mUserAvatarImg;
//    @BindView(R.id.user_name_tv) TextView mUserNameTv;
//    @BindView(R.id.created_at_tv) TextView mCreatedAtTv;
//    @BindView(R.id.describe_tv) TextView mContentTv;
//    @BindView(R.id.re_describe_tv) TextView mReContentTv;
//    @BindView(R.id.share_photo_view) SharePhotoView mSharePhotoView;
//
//    public TimelineViewHolder(View itemView) {
//      super(itemView);
//      ButterKnife.bind(this, itemView);
//    }
//  }
//}
