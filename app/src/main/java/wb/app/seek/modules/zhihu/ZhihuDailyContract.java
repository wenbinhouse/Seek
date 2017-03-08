package wb.app.seek.modules.zhihu;

import java.util.List;

import wb.app.seek.common.base.mvp.IView;
import wb.app.seek.model.ZhihuDailyNews;
import wb.app.seek.model.ZhihuDailyStory;

/**
 * Created by W.b on 2017/2/10.
 */
class ZhihuDailyContract {

  interface View extends IView {

    void showNews(ZhihuDailyNews dailyNews);

    void showTopStory(List<ZhihuDailyStory> dailyStoryList);

    void showMoreNews(ZhihuDailyNews dailyNews);

    void showNoMoreNews();
  }

  interface Presenter {

    void refreshNews(String date);

    void loadMoreNews();

    void queryLatest();
  }
}
