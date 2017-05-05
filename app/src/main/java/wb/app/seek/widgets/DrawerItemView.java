package wb.app.seek.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.app.seek.R;

/**
 * Created by W.b on 2017/3/14.
 */
public class DrawerItemView extends LinearLayout {

    @BindView(R.id.drawer_item_tv) TextView mDrawerItemTv;

    public DrawerItemView(Context context) {
        this(context, null);
    }

    public DrawerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_drawer_item, this);
        ButterKnife.bind(this);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DrawerItemView);
            String text = typedArray.getString(R.styleable.DrawerItemView_divText);
            int imageResId = typedArray.getResourceId(R.styleable.DrawerItemView_divImage, -1);
            typedArray.recycle();

            if (!TextUtils.isEmpty(text)) {
                mDrawerItemTv.setText(text);
            }

            if (imageResId != -1) {
                Drawable drawable = context.getResources().getDrawable(imageResId);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mDrawerItemTv.setCompoundDrawables(drawable, null, null, null);
            }
        }
    }
}
