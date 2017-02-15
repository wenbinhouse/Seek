package wb.app.seek.modules.zhihu;

import wb.app.seek.common.base.mvp.IView;

/**
 * Created by W.b on 2017/2/10.
 */
class ZhihuDailyDetailContract {

  interface View extends IView {

    void showCoverImg(String url);

    void showTitle(String title);

    void showWeb(String body);
  }

  interface Presenter {

    void getDetail(int storyId);
  }
}
