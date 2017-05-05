package wb.app.seek.modules.zhihu.adapter;

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
import wb.app.seek.modules.model.ZhihuDailyNews;
import wb.app.seek.modules.model.ZhihuDailyStory;
import wb.app.seek.widgets.banner.BannerView;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int VIEW_TYPE_HEADER = 1;
  private static final int VIEW_TYPE_FOOTER = 2;
  private List<ZhihuDailyStory> mZhihuStories;
  private Context mContext;
  private boolean mHasMore;
  private List<ZhihuDailyStory> mDailyStoryList;

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    if (viewType == VIEW_TYPE_HEADER) {
      return new HeaderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_header, parent, false));

    } else if (viewType == VIEW_TYPE_FOOTER) {
      return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_footer, parent, false));
    }

    return new ZhihuDailyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_zhihu, parent, false));
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    if (viewHolder instanceof ZhihuDailyViewHolder) {
      final ZhihuDailyViewHolder holder = (ZhihuDailyViewHolder) viewHolder;
      ZhihuDailyStory story = mZhihuStories.get(position - 1);
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
            mListener.onClick((int) holder.itemView.getTag(), holder.mItemCoverImg);
          }
        }
      });

    } else if (viewHolder instanceof FooterViewHolder) {
      FooterViewHolder holder = (FooterViewHolder) viewHolder;
      holder.mItemLoadingPb.setVisibility(mHasMore ? View.VISIBLE : View.GONE);
      holder.mItemLoadingStatusTv.setText(mHasMore ? mContext.getText(R.string.loading_more) : mContext.getText(R.string.no_more));

    } else if (viewHolder instanceof HeaderViewHolder) {
      HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
      holder.mBannerView.setBanner(mDailyStoryList);
      holder.mBannerView.setBannerClickListener(new BannerView.BannerClickListener() {
        @Override
        public void onBannerClick(int id) {
          if (mListener != null) {
            mListener.onBannerClick(id);
          }
        }
      });
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (position == 0) {
      return VIEW_TYPE_HEADER;
    } else if (position == getItemCount() - 1) {
      return VIEW_TYPE_FOOTER;
    }
    return super.getItemViewType(position);
  }

  @Override
  public int getItemCount() {
//    return mZhihuStories != null && mZhihuStories.size() > 0 ? mZhihuStories.size() + 2 : 1;
    return getDataCount() > 0 ? getDataCount() + 2 : 1;
  }

  public int getDataCount() {
    if (mZhihuStories != null && mZhihuStories.size() > 0) {
      return mZhihuStories.size();
    }

    return 0;
  }

  public void showNews(ZhihuDailyNews dailyNews) {
    mHasMore = true;
    if (dailyNews != null) {
      mZhihuStories = dailyNews.getStories();
      notifyDataChanged();
    }
  }

  public void showMoreNews(ZhihuDailyNews dailyNews) {
    mHasMore = true;
    if (dailyNews != null && dailyNews.getStories() != null && dailyNews.getStories().size() > 0) {
      mZhihuStories.addAll(dailyNews.getStories());
      notifyDataChanged();
    }
  }

  public void showNoMoreNews() {
    mHasMore = false;
    notifyDataChanged();
  }

  public void notifyDataChanged() {
    notifyItemRangeChanged(1, getDataCount());
  }

  public void showTopStory(List<ZhihuDailyStory> dailyStoryList) {
    mDailyStoryList = dailyStoryList;
    notifyItemChanged(0);
  }

  class ZhihuDailyViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_title_tv) TextView mItemTitleTv;
    @BindView(R.id.item_cover_img) ImageView mItemCoverImg;

    public ZhihuDailyViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class FooterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_loading_pb) ProgressBar mItemLoadingPb;
    @BindView(R.id.item_loading_status_tv) TextView mItemLoadingStatusTv;

    public FooterViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class HeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.bannerView) BannerView mBannerView;

    public HeaderViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public OnItemClickListener mListener;

  public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
  }

  public interface OnItemClickListener {
    void onClick(int id, View view);

    void onBannerClick(int id);
  }
}
