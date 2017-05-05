package wb.app.seek.modules.zhihu.presenter;

import wb.app.seek.common.base.mvp.IView;

/**
 * Created by W.b on 2017/2/10.
 */
public class ZhihuDailyDetailContract {

    public interface View extends IView {

        void showCoverImg(String url);

        void showTitle(String title);

        void showWeb(String body);

        void showBrowserNotFoundError();
    }

    public interface Presenter {

        void getDetail(int storyId);
    }
}
