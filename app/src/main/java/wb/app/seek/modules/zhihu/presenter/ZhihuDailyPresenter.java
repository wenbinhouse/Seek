package wb.app.seek.modules.zhihu.presenter;

import android.text.TextUtils;

import wb.app.seek.common.base.mvp.BasePresenter;
import wb.app.seek.common.http.exception.ExceptionHandler;
import wb.app.seek.common.http.rx.BaseSubscriber;
import wb.app.seek.common.http.rx.ResponseTransformer;
import wb.app.seek.common.utils.DateTimeUtils;
import wb.app.seek.modules.model.ZhihuDailyNews;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyPresenter extends BasePresenter<ZhihuDailyContract.View> implements ZhihuDailyContract.Presenter {

    private String mBeforeDay;

    @Override
    public void refreshNews(String date) {
        if (!DateTimeUtils.isValid(date)) {
            getView().showError("日期不能小于2013-05-20");
            return;
        }

        getView().showLoading();

        getService().getZhihuNewsByDate(date)
                .compose(ResponseTransformer.<ZhihuDailyNews>getInstance(mLifecycleSubject))
                .subscribe(new BaseSubscriber<ZhihuDailyNews>() {
                    @Override
                    public void onSuccess(ZhihuDailyNews data) {
                        if (data == null || data.getStories() == null || data.getStories().size() == 0)
                            getView().showEmptyView();

                        if (data != null)
                            mBeforeDay = data.getDate();

                        if (isAttach())
                            getView().showNews(data);
                    }

                    @Override
                    public void onFailure(String msg, int errorCode) {
                        if (isAttach()) {
                            getView().showError(msg);
                            if (errorCode == ExceptionHandler.NETWORK_EXCEPTION) {
                                getView().showNetErrorView();
                            }
                        }
                    }

                    @Override
                    public void onFinish() {
                        if (isAttach())
                            getView().hideLoading();
                    }
                });
    }

    @Override
    public void loadMoreNews() {
        if (TextUtils.isEmpty(mBeforeDay)) {
            return;
        }

        getService().getZhihuNewsByDate(mBeforeDay)
                .compose(ResponseTransformer.<ZhihuDailyNews>getInstance(mLifecycleSubject))
                .subscribe(new BaseSubscriber<ZhihuDailyNews>() {

                    @Override
                    public void onSuccess(ZhihuDailyNews data) {
                        if (!isAttach()) {
                            return;
                        }

                        if (data != null && data.getStories() != null && data.getStories().size() > 0) {
                            mBeforeDay = data.getDate();
                            getView().showMoreNews(data);
                        } else {
                            getView().showNoMore();
                        }
                    }

                    @Override
                    public void onFailure(String msg, int errorCode) {
                        if (isAttach())
                            getView().showError(msg);
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    @Override
    public void queryLatest() {
        getService().getZhiHuNewsLatest()
                .compose(ResponseTransformer.<ZhihuDailyNews>getInstance(mLifecycleSubject))
                .subscribe(new BaseSubscriber<ZhihuDailyNews>() {
                    @Override
                    public void onSuccess(ZhihuDailyNews data) {
                        if (!isAttach()) {
                            return;
                        }

                        if (data != null && data.getTop_stories() != null) {
                            getView().showTopStory(data.getTop_stories());
                        }
                    }

                    @Override
                    public void onFailure(String msg, int errorCode) {
                        if (!isAttach()) {
                            return;
                        }

                        getView().showError(msg);
                    }

                    @Override
                    public void onFinish() {
                    }
                });
    }
}
