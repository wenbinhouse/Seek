package wb.app.seek.modules.zhihu;

import wb.app.seek.common.base.mvp.BaseView;
import wb.app.seek.model.ZhihuDailyNews;

/**
 * Created by W.b on 2017/2/10.
 */
class ZhihuDailyContract {

  interface View extends BaseView {

    void showNews(ZhihuDailyNews dailyNews);

    void showMoreNews(ZhihuDailyNews dailyNews);

    void showNoMoreNews();
  }

  interface Presenter {

    void refreshNews(String date);

    void loadMoreNews();
  }
}
