package wb.app.seek.modules.zhihu;

import wb.app.seek.common.base.mvp.BaseView;
import wb.app.seek.model.ZhihuDailyNews;

/**
 * Created by W.b on 2017/2/10.
 */
public class ZhihuDailyContract {

  interface View extends BaseView {

    void showStories(ZhihuDailyNews data);
  }

  interface Presenter {

    void getStories();
  }
}
