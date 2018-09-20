package wb.app.seek.modules.zhihu.presenter;

import java.util.List;

import wb.app.seek.common.base.mvp.impl.IView;
import wb.app.seek.modules.model.ZhihuDailyNews;
import wb.app.seek.modules.model.ZhihuDailyStory;

/**
 * Created by W.b on 2017/2/10.
 */
public class ZhihuDailyContract {

    public interface View extends IView {

        void showNews(ZhihuDailyNews dailyNews);

        void showMoreNews(ZhihuDailyNews dailyNews);

        void showTopStory(List<ZhihuDailyStory> dailyStoryList);

//        void showNoMoreNews();
    }

    public interface Presenter {

        void refreshNews(String date);

        void loadMoreNews();

        void queryLatest();
    }
}
