package wb.app.seek.widgets.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by W.b on 04/05/2017.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private static final int ITEM_TYPE_NORMAL = 1, ITEM_TYPE_FOOTER = 2;

    protected final Context mContext;

    private List<T> mDataList = new ArrayList<>();

    private View footView;
    private int footViewSize = 0;
    private boolean isAddFoot = false;

    /**
     * 获取布局资源 Id
     */
    protected abstract int getItemLayoutResId();

    /**
     * 获取 ViewHolder
     */
    protected abstract BaseRecyclerViewHolder getViewHolder(View itemView);

    /**
     * 默认的页脚布局
     */
    protected BaseRecyclerViewHolder getFooterViewHolder(View footerView) {
        return new DefaultFooterViewHolder(footerView);
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
                return getViewHolder(itemView);

            case ITEM_TYPE_FOOTER:
                itemView = footView;
                return getFooterViewHolder(itemView);
        }
        return getViewHolder(itemView);
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
        if (footViewSize == 1 && position == getItemCount() - 1) {
            type = ITEM_TYPE_FOOTER;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        return mDataList.size() + footViewSize;
    }

    public void addFootView(View view) {
        footView = view;
        footViewSize = 1;
        isAddFoot = true;
    }

    public void removeFootView() {
        footView = null;
        footViewSize = 0;
        isAddFoot = false;
    }

    protected void bindFooterViewHolder(BaseRecyclerViewHolder viewHolder) {

    }

    protected abstract void bindItemViewHolder(BaseRecyclerViewHolder viewHolder, final int position);

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
