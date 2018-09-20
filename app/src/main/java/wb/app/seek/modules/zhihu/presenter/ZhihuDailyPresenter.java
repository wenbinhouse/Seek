package wb.app.seek.modules.zhihu.presenter;

import android.text.TextUtils;

import wb.app.seek.common.base.mvp.BasePresenter;
import wb.app.seek.common.http.exception.ExceptionHandler;
import wb.app.seek.common.http.rx.BaseObserver;
import wb.app.seek.common.http.rx.LifecycleTransformer;
import wb.app.seek.common.utils.DateTimeUtils;
import wb.app.seek.modules.model.ZhihuDailyNews;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyPresenter extends BasePresenter<ZhihuDailyContract.View> implements ZhihuDailyContract.Presenter {

    private String mBeforeDay;

    @Override
    public void refreshNews(String date) {
        if (!isAttachView())
            return;

        if (!DateTimeUtils.isValid(date)) {
            getView().showError("日期不能小于2013-05-20");
            return;
        }

        getView().showLoading();

        getService().getZhihuNewsByDate(date)
                .compose(new LifecycleTransformer<ZhihuDailyNews>(mLifecycleEventBehaviorSubject))
                .subscribe(new BaseObserver<ZhihuDailyNews>() {
                    @Override
                    public void onSuccess(ZhihuDailyNews data) {
                        if (!isAttachView())
                            return;

                        if (data == null && data.getStories() == null || data.getStories().size() == 0)
                            getView().showEmptyView();

                        mBeforeDay = data.getDate();

                        getView().showNews(data);
                    }

                    @Override
                    public void onFailure(String msg, int errorCode) {
                        if (!isAttachView())
                            return;

                        getView().showError(msg);
                        if (errorCode == ExceptionHandler.NETWORK_EXCEPTION) {
                            getView().showNetErrorView();
                        }
                    }

                    @Override
                    public void onFinish() {
                        if (!isAttachView())
                            return;

                        getView().hideLoading();
                    }
                });
    }

    @Override
    public void loadMoreNews() {
        if (!isAttachView())
            return;

        if (TextUtils.isEmpty(mBeforeDay)) {
            return;
        }

        getService().getZhihuNewsByDate(mBeforeDay)
                .compose(new LifecycleTransformer<ZhihuDailyNews>(mLifecycleEventBehaviorSubject))
                .subscribe(new BaseObserver<ZhihuDailyNews>() {

                    private boolean isHasMore = true;

                    @Override
                    public void onSuccess(ZhihuDailyNews data) {
                        if (!isAttachView())
                            return;

                        if (data != null && data.getStories() != null && data.getStories().size() > 0) {
                            isHasMore = true;
                            mBeforeDay = data.getDate();
                            getView().showMoreNews(data);
                        } else {
                            isHasMore = false;
                        }
                    }

                    @Override
                    public void onFailure(String msg, int errorCode) {
                        if (!isAttachView())
                            return;

                        getView().showError(msg);
                    }

                    @Override
                    public void onFinish() {
                        if (!isAttachView())
                            return;

                        getView().hideLoading();
                        if (!isHasMore) {
                            getView().showNoMore();
                        }
                    }
                });
    }

    @Override
    public void queryLatest() {
        if (!isAttachView())
            return;

        getService().getZhiHuNewsLatest()
                .compose(new LifecycleTransformer<ZhihuDailyNews>(mLifecycleEventBehaviorSubject))
                .subscribe(new BaseObserver<ZhihuDailyNews>() {
                    @Override
                    public void onSuccess(ZhihuDailyNews data) {
                        if (!isAttachView())
                            return;

                        if (data.getTop_stories() != null) {
                            getView().showTopStory(data.getTop_stories());
                        }
                    }

                    @Override
                    public void onFailure(String msg, int errorCode) {
                        if (!isAttachView())
                            return;

                        getView().showError(msg);
                    }

                    @Override
                    public void onFinish() {
                        if (!isAttachView())
                            return;

                        getView().hideLoading();
                    }
                });
    }
}
