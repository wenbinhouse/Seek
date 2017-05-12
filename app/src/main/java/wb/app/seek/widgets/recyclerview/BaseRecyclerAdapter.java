package wb.app.seek.widgets.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wb.app.seek.R;

/**
 * Created by W.b on 04/05/2017.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private static final int ITEM_TYPE_NORMAL = 1, ITEM_TYPE_FOOTER = 2;

    protected final Context mContext;

    private List<T> mDataList = new ArrayList<>();

    protected boolean isAddFooter = false;

    /**
     * 获取布局资源 Id
     */
    protected abstract int getItemLayoutResId();

    /**
     * 获取列表底部布局 资源 id
     */
    protected int getFooterLayoutResId() {
        return R.layout.item_footer;
    }

    /**
     * item ViewHolder
     */
    protected abstract BaseRecyclerViewHolder getItemViewHolder(View itemView);

    /**
     * footer ViewHolder
     */
    protected BaseRecyclerViewHolder getFooterViewHolder(View footerView) {
        return new DefaultFooterAdapter.DefaultFooterViewHolder(footerView);
    }

    /**
     * bind item
     */
    protected abstract void bindItemViewHolder(BaseRecyclerViewHolder viewHolder, final int position);

    /**
     * bind footer
     */
    protected void bindFooterViewHolder(BaseRecyclerViewHolder viewHolder) {

    }

    public BaseRecyclerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM_TYPE_NORMAL:
                itemView = inflater.inflate(getItemLayoutResId(), parent, false);
                return getItemViewHolder(itemView);

            case ITEM_TYPE_FOOTER:
                itemView = inflater.inflate(getFooterLayoutResId(), parent, false);
                return getFooterViewHolder(itemView);
        }
        return getItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_TYPE_NORMAL:
                bindItemViewHolder(viewHolder, position);
                break;

            case ITEM_TYPE_FOOTER:
                bindFooterViewHolder(viewHolder);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = ITEM_TYPE_NORMAL;
        if (isAddFooter && position == getItemCount() - 1) {
            type = ITEM_TYPE_FOOTER;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        return isAddFooter ? mDataList.size() + 1 : mDataList.size();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public int getDataListSize() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    public void addData(List<T> data) {
        if (data == null) {
            return;
        }

        mDataList.clear();
        mDataList.addAll(data);

        notifyDataSetChanged();
    }

    public void addMoreData(List<T> data) {
        int lastPosition = mDataList.size();
        mDataList.addAll(data);

        notifyItemRangeChanged(lastPosition, mDataList.size());
    }

    public T getItem(int position) {
        if (mDataList == null || mDataList.size() == 0) {
            return null;
        }
        return mDataList.get(getItemPosition(position));
    }

    /**
     * 获取item所对应的position
     */
    protected int getItemPosition(int position) {
        return position;
    }

    protected OnItemCommonClickListener mListener;

    public void setOnItemCommonClickListener(OnItemCommonClickListener listener) {
        mListener = listener;
    }

    public interface OnItemCommonClickListener {
        void onItemClick(int position);
    }
}
