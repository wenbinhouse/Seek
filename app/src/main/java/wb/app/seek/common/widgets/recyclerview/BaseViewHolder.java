package wb.app.seek.common.widgets.recyclerview;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by W.b on 2017/1/11.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

  public BaseViewHolder(View itemView) {
    super(itemView);
  }

//  public BaseViewHolder(ViewGroup parent, @LayoutRes int resId) {
//    super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
//  }

  protected View getView(@IdRes int viewId) {
    return itemView.findViewById(viewId);
  }

  public abstract void onBindView(T data);
}
