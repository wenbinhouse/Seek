package wb.app.seek.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wb.app.seek.R;

/**
 * Created by W.b on 06/04/2017.
 */
public class SettingItemView extends LinearLayout {

  @BindView(R.id.titleTv) TextView mTitleTv;
  @BindView(R.id.subTitleTv) TextView mSubTitleTv;
  @BindView(R.id.checkBox) CheckBox mCheckBox;

  public SettingItemView(Context context) {
    this(context, null);
  }

  public SettingItemView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    LayoutInflater.from(context).inflate(R.layout.view_setting_item, this);
    ButterKnife.bind(this);

    if (attrs != null) {
      TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);
      String title = ta.getString(R.styleable.SettingItemView_sivText);
      String subTitle = ta.getString(R.styleable.SettingItemView_sivSubText);
      boolean showCheck = ta.getBoolean(R.styleable.SettingItemView_sivShowCheck, false);
      boolean isChecked = ta.getBoolean(R.styleable.SettingItemView_sivChecked, false);
      ta.recycle();

      if (!TextUtils.isEmpty(title)) {
        mTitleTv.setText(title);
      }

      if (!TextUtils.isEmpty(subTitle)) {
        mSubTitleTv.setText(subTitle);
        mSubTitleTv.setVisibility(VISIBLE);
      }

      if (showCheck) {
        mCheckBox.setChecked(isChecked);
        mCheckBox.setVisibility(VISIBLE);
      } else {
        mCheckBox.setVisibility(GONE);
      }
    }
  }

  public CheckBox getCheckBox() {
    return mCheckBox;
  }

  public void setChecked(boolean isChecked) {
    mCheckBox.setChecked(isChecked);
  }
}
