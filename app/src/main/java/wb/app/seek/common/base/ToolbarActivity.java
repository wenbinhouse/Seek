package wb.app.seek.common.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import wb.app.seek.R;

/**
 * Created by W.b on 2017/1/11.
 */
public class ToolbarActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
//    super.setContentView(layoutResID);
        RelativeLayout contentView = new RelativeLayout(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        LayoutInflater inflater = LayoutInflater.from(this);

        View userView = inflater.inflate(layoutResID, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int toolBarSize = (int) getResources().getDimension(R.dimen.tool_bar_height);
        if (isOverLayout()) {
            layoutParams.topMargin = 0;
        } else {
            layoutParams.topMargin = toolBarSize;
        }
        contentView.addView(userView, layoutParams);

        View view = inflater.inflate(R.layout.view_toolbar, contentView);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tool_bar);

        setContentView(contentView);
        setSupportActionBar(toolbar);
    }

    protected boolean isOverLayout() {
        return false;
    }

    public void setContentViewNoToolbar(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
