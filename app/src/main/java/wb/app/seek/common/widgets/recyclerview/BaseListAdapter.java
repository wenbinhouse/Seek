package wb.app.seek.common.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.app.seek.R;

/**
 * Created by W.b on 2017/1/11.
 */
public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

  protected static final int VIEW_TYPE_FOOTER = 1;
  protected List<T> mData = new ArrayList<>();
  private boolean mIsLoading;
  private boolean mIsNoMore;

  public void setLoading(boolean loading) {
    mIsLoading = loading;
  }

  public void setNoMore(boolean noMore) {
    mIsNoMore = noMore;
  }

  @Override
  public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == VIEW_TYPE_FOOTER) {
      return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recyclerview_footer, parent, false));
    }
    return onCreateHolder(parent, viewType);
  }

  protected abstract BaseViewHolder onCreateHolder(ViewGroup parent, int viewType);

  @Override
  public void onBindViewHolder(BaseViewHolder holder, int position) {

    if (mData == null || mData.size() == 0) {
      return;
    }

    if (position < mData.size() - 1) {
      holder.onBindView(mData.get(position));
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
    return mData != null && mData.size() > 0 ? mData.size() + 1 : 0;
  }

  public void initData(List<T> data) {
    if (data == null) {
      data = new ArrayList<>();
    }
    mData.addAll(data);
    notifyDataSetChanged();
  }

  public void addData(List<T> data) {
    mData.addAll(data);
    notifyDataSetChanged();
  }

  public class FooterViewHolder extends BaseViewHolder {

    @BindView(R.id.loading_pb) ProgressBar mLoadingPb;
    @BindView(R.id.loading_tv) TextView mLoadingTv;
    @BindView(R.id.no_more_tv) TextView mNoMoreTv;

    public FooterViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindView(Object data) {
      if (mIsNoMore) {
        mNoMoreTv.setVisibility(View.VISIBLE);
        mLoadingPb.setVisibility(View.GONE);
        mLoadingTv.setVisibility(View.GONE);
      } else {
        mNoMoreTv.setVisibility(View.GONE);
        mLoadingPb.setVisibility(View.VISIBLE);
        mLoadingTv.setVisibility(View.VISIBLE);
      }
    }
  }
}
