package wb.app.seek.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import wb.app.seek.R;

/**
 * 多种状态切换的布局
 * 无网络、无数据
 * <p>
 * <p>
 * Created by W.b on 05/05/2017.
 */
public class MultiStatusLayout extends FrameLayout {

    private LayoutInflater mLayoutInflater;
    private int mContentViewId;
    private int mNoNetworkViewId;
    private int mEmptyViewId;

    private View mCurrentView;

    private boolean mIsFirstIn;

    public MultiStatusLayout(@NonNull Context context) {
        this(context, null);
    }

    public MultiStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiStatusLayout);
        mContentViewId = typedArray.getResourceId(R.styleable.MultiStatusLayout_msl_content_view_id, -1);
        mNoNetworkViewId = typedArray.getResourceId(R.styleable.MultiStatusLayout_msl_no_network_view, R.layout.msl_no_network_view);
        mEmptyViewId = typedArray.getResourceId(R.styleable.MultiStatusLayout_msl_empty_view, R.layout.msl_empty_view);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLayoutInflater = LayoutInflater.from(getContext());
    }

    public void showNoNetworkView() {
        View noNetworkView = mLayoutInflater.inflate(mNoNetworkViewId, null);

        if (mCurrentView == noNetworkView)
            return;

        removeView(mCurrentView);
        addView(noNetworkView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mCurrentView = noNetworkView;
    }

    public void showEmptyView() {
        View emptyView = mLayoutInflater.inflate(mEmptyViewId, null);

        if (mCurrentView == emptyView)
            return;

        removeView(mCurrentView);
        addView(emptyView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mCurrentView = emptyView;
    }

    public void showContentView() {
        View contentView = findViewById(mContentViewId);

        if (mCurrentView == contentView)
            return;

        removeAllViews();
        addView(contentView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mCurrentView = contentView;
    }
}
