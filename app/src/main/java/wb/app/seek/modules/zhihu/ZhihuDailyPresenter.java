package wb.app.seek.modules.zhihu;

import wb.app.seek.common.base.mvp.BasePresenter;
import wb.app.seek.common.http.rx.BaseSubscriber;
import wb.app.seek.common.http.rx.ResponseTransformer;
import wb.app.seek.model.ZhihuDailyNews;
import wb.app.seek.utils.DateTimeUtils;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyPresenter extends BasePresenter<ZhihuDailyContract.View> implements ZhihuDailyContract.Presenter {

  private boolean mIsLoadingMore;
  private String mBeforeDay;

  @Override
  public void refreshNews(String date) {
    if (!DateTimeUtils.isValid(date)) {
      getView().showError("日期不能小于2013-05-20", "");
      return;
    }

    getView().showLoading();

    getService().getZhihuNewsByDate(date)
        .compose(ResponseTransformer.<ZhihuDailyNews>getInstance(mLifecycleSubject))
        .subscribe(new BaseSubscriber<ZhihuDailyNews>() {
          @Override
          public void onSuccess(ZhihuDailyNews data) {
            if (data != null)
              mBeforeDay = data.getDate();

            if (isAttach())
              getView().showNews(data);
          }

          @Override
          public void onFailure(String msg, String exception) {
            if (isAttach())
              getView().showError(msg, exception);
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
    if (mIsLoadingMore) {
      return;
    }

    mIsLoadingMore = true;

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
              getView().showNoMoreNews();
            }
          }

          @Override
          public void onFailure(String msg, String exception) {
            if (isAttach())
              getView().showError(msg, exception);
          }

          @Override
          public void onFinish() {
            mIsLoadingMore = false;
          }
        });
  }
}
