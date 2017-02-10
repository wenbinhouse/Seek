package wb.app.seek.modules.zhihu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class ZhihuDailyAdapter extends RecyclerView.Adapter<ZhihuDailyAdapter.ZhihuDailyViewholder> {

  private List<ZhihuDailyStory> mZhihuStories;
  private Context mContext;

  @Override
  public ZhihuDailyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    return new ZhihuDailyViewholder(LayoutInflater.from(mContext).inflate(R.layout.item_zhihu, parent, false));
  }

  @Override
  public void onBindViewHolder(final ZhihuDailyViewholder holder, int position) {
    ZhihuDailyStory story = mZhihuStories.get(position);
    holder.mItemTitleTv.setText(story.getTitle());
    if (story.getImages() != null && story.getImages().size() > 0) {
      Glide.with(mContext)
          .load(story.getImages().get(0))
          .centerCrop()
          .placeholder(R.mipmap.ic_launcher)
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
  }

  @Override
  public int getItemCount() {
    return mZhihuStories != null && mZhihuStories.size() > 0 ? mZhihuStories.size() : 0;
  }

  public void setStories(ZhihuDailyNews data) {
    if (data != null) {
      mZhihuStories = data.getStories();
      notifyDataSetChanged();
    }
  }

  public class ZhihuDailyViewholder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_title_tv) TextView mItemTitleTv;
    @BindView(R.id.item_cover_img) ImageView mItemCoverImg;

    public ZhihuDailyViewholder(View itemView) {
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
