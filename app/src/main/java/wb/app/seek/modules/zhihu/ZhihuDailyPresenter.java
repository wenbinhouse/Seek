package wb.app.seek.modules.zhihu;

import wb.app.seek.common.base.mvp.BasePresenter;
import wb.app.seek.common.http.rx.BaseSubscriber;
import wb.app.seek.model.ZhihuDailyNews;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyPresenter extends BasePresenter<ZhihuDailyContract.View> implements ZhihuDailyContract.Presenter {

  @Override
  public void getStories() {
    getView().showLoading();

    getService().getZhiHuNewsLatest()
        .compose(this.<ZhihuDailyNews>bindLifecycleEvent())
        .subscribe(new BaseSubscriber<ZhihuDailyNews>() {
          @Override
          public void onSuccess(ZhihuDailyNews data) {
            getView().showStories(data);
          }

          @Override
          public void onFailure(String msg, String exception) {
            getView().showError(msg, exception);
          }

          @Override
          public void onFinish() {
            getView().hideLoading();
          }
        });
  }
}
