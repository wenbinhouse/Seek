package wb.app.seek.modules.zhihu.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import wb.app.seek.R;
import wb.app.seek.modules.model.ZhihuDailyStory;
import wb.app.seek.widgets.recyclerview.BaseRecyclerAdapter;
import wb.app.seek.widgets.recyclerview.BaseRecyclerViewHolder;

/**
 * Created by W.b on 04/05/2017.
 */
public class ZhihuDailyAdapterNew extends BaseRecyclerAdapter<ZhihuDailyStory> {

    public ZhihuDailyAdapterNew(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId() {
        return R.layout.item_zhihu;
    }

    @Override
    protected BaseRecyclerViewHolder getViewHolder(View itemView) {
        return new TestViewHolder(itemView);
    }

    @Override
    protected void bindItemViewHolder(BaseRecyclerViewHolder viewHolder, final int position) {
        final TestViewHolder holder = (TestViewHolder) viewHolder;
        final ZhihuDailyStory story = getItem(position);
        holder.mItemTitleTv.setText(story.getTitle());
        if (story.getImages() != null && story.getImages().size() > 0) {
            Glide.with(mContext)
                    .load(story.getImages().get(0))
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .crossFade()
                    .into(holder.mItemCoverImg);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(story.getId(), holder.mItemCoverImg);
                }
            }
        });
    }

    class TestViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.item_title_tv) TextView mItemTitleTv;
        @BindView(R.id.item_cover_img) ImageView mItemCoverImg;

        public TestViewHolder(View itemView) {
            super(itemView);
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
