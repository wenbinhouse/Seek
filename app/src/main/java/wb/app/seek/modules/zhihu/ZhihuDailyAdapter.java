package wb.app.seek.modules.zhihu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.app.seek.R;
import wb.app.seek.model.ZhihuDailyNews;
import wb.app.seek.model.ZhihuDailyStory;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int VIEW_TYPE_FOOTER = 1;
  private List<ZhihuDailyStory> mZhihuStories;
  private Context mContext;
  private boolean mHasMore;

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    if (viewType == VIEW_TYPE_FOOTER) {
      return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_footer, parent, false));
    }
    return new ZhihuDailyViewholder(LayoutInflater.from(mContext).inflate(R.layout.item_zhihu, parent, false));
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    if (viewHolder instanceof ZhihuDailyViewholder) {
      final ZhihuDailyViewholder holder = (ZhihuDailyViewholder) viewHolder;
      ZhihuDailyStory story = mZhihuStories.get(position);
      holder.mItemTitleTv.setText(story.getTitle());
      if (story.getImages() != null && story.getImages().size() > 0) {
        Glide.with(mContext)
            .load(story.getImages().get(0))
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .crossFade()
            .into(holder.mItemCoverImg);
      }

      holder.itemView.setTag(story.getId());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          holder.itemView.getTag();
          if (mListener != null) {
            mListener.onClick((int) holder.itemView.getTag());
          }
        }
      });

    } else if (viewHolder instanceof FooterViewHolder) {
      FooterViewHolder holder = (FooterViewHolder) viewHolder;
      holder.mItemLoadingPb.setVisibility(mHasMore ? View.VISIBLE : View.GONE);
      holder.mItemLoadingStatusTv.setText(mHasMore ? mContext.getText(R.string.loading_more) : mContext.getText(R.string.no_more));
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (position == getItemCount() - 1) {
      return VIEW_TYPE_FOOTER;
    }
    return super.getItemViewType(position);
  }

  @Override
  public int getItemCount() {
    return mZhihuStories != null && mZhihuStories.size() > 0 ? mZhihuStories.size() + 1 : 0;
  }

  public void showNews(ZhihuDailyNews dailyNews) {
    mHasMore = true;
    if (dailyNews != null) {
      mZhihuStories = dailyNews.getStories();
      notifyDataSetChanged();
    }
  }

  public void showMoreNews(ZhihuDailyNews dailyNews) {
    mHasMore = true;
    if (dailyNews != null && dailyNews.getStories() != null && dailyNews.getStories().size() > 0) {
      mZhihuStories.addAll(dailyNews.getStories());
      notifyDataSetChanged();
    }
  }

  public void showNoMoreNews() {
    mHasMore = false;
    notifyDataSetChanged();
  }

  public class ZhihuDailyViewholder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_title_tv) TextView mItemTitleTv;
    @BindView(R.id.item_cover_img) ImageView mItemCoverImg;

    public ZhihuDailyViewholder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public class FooterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_loading_pb) ProgressBar mItemLoadingPb;
    @BindView(R.id.item_loading_status_tv) TextView mItemLoadingStatusTv;

    public FooterViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public OnItemClickListener mListener;

  public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
  }

  public interface OnItemClickListener {
    void onClick(int id);
  }
}
