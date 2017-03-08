package wb.app.seek.widgets.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import wb.app.seek.model.ZhihuDailyStory;

/**
 * Created by W.b on 2017/3/7.
 */
public class BannerAdapter extends PagerAdapter {

  private static final int MAX_COUNT = 10000;

  private List<String> mImageList = new ArrayList<>();
  private List<ZhihuDailyStory> mDailyStoryList = new ArrayList<>();
  private BannerItemClickListener mListener;

  public BannerAdapter(BannerItemClickListener listener) {
    mListener = listener;
  }

  @Override
  public int getCount() {
    return mImageList.size() > 0 ? MAX_COUNT : 0;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    ImageView imageView = new ImageView(container.getContext());
    Glide.with(container.getContext())
        .load(mImageList.get(position % mImageList.size()))
        .centerCrop()
        .crossFade()
        .into(imageView);

    imageView.setTag(mDailyStoryList.get(position % mImageList.size()));
    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ZhihuDailyStory dailyStory = (ZhihuDailyStory) v.getTag();
        if (dailyStory != null && mListener != null) {
          mListener.onItemClick(dailyStory);
        }
      }
    });

    container.addView(imageView);
    return imageView;
  }

  public void setBanner(List<ZhihuDailyStory> dailyStoryList) {
    mImageList.clear();
    mDailyStoryList.clear();
    mDailyStoryList.addAll(dailyStoryList);
    for (ZhihuDailyStory zhihuDailyStory : dailyStoryList) {
      mImageList.add(zhihuDailyStory.getImage());
    }
    notifyDataSetChanged();
  }

  public interface BannerItemClickListener {

    void onItemClick(ZhihuDailyStory dailyStory);

  }
}
