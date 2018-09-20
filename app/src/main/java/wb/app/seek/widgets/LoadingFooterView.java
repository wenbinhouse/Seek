package wb.app.seek.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import wb.app.seek.R;

/**
 * 列表底部 loading
 * <p>
 * Created by W.b on 05/05/2017.
 */
public class LoadingFooterView {

    public static final int STATE_INIT = 1;
    public static final int STATE_IDLE = 2;
    public static final int STATE_LOADING = 3;
    public static final int STATE_END = 4;

    private final View mView;
    private final ProgressBar mProgressBar;
    private final TextView mTextView;

    protected int mCurrentState = STATE_IDLE;

    private long mAnimDuration;

    public LoadingFooterView(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.item_footer, null);
        mProgressBar = (ProgressBar) mView.findViewById(R.id.item_loading_pb);
        mTextView = (TextView) mView.findViewById(R.id.item_loading_status_tv);

        mView.setVisibility(View.GONE);
        mAnimDuration = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
    }

    public void setState(int state) {

        if (mCurrentState == state)
            return;

        mCurrentState = state;

        mView.setVisibility(View.VISIBLE);

        switch (state) {
            case STATE_LOADING:
                mTextView.setText("加载中...");
                mTextView.setVisibility(View.VISIBLE);
                mTextView.animate().withLayer().alpha(1).setDuration(mAnimDuration);
                mProgressBar.setVisibility(View.VISIBLE);
                break;
            case STATE_END:
                mTextView.setText("没有更多了");
                mTextView.setVisibility(View.VISIBLE);
                mTextView.animate().withLayer().alpha(1).setDuration(mAnimDuration);
                mProgressBar.setVisibility(View.GONE);
                break;
            default:
                mView.setVisibility(View.GONE);
                break;
        }
    }

    public View getView() {
        return mView;
    }
}
