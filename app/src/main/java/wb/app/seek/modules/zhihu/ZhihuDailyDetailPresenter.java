package wb.app.seek.modules.zhihu;

import wb.app.seek.common.base.mvp.BasePresenter;
import wb.app.seek.common.http.rx.BaseSubscriber;
import wb.app.seek.model.ZhihuDailyStory;

/**
 * Created by W.b on 2017/2/10.
 */
public class ZhihuDailyDetailPresenter extends BasePresenter<ZhihuDailyDetailContract.View> implements ZhihuDailyDetailContract.Presenter {

  @Override
  public void getDetail(int storyId) {
    getView().showLoading();

    getService().getZhihuNewsDetail(storyId)
        .compose(this.<ZhihuDailyStory>bindLifecycleEvent())
        .subscribe(new BaseSubscriber<ZhihuDailyStory>() {
          @Override
          public void onSuccess(ZhihuDailyStory data) {
            if (data != null) {
              getView().showTitle(data.getTitle());
              getView().showCoverImg(data.getImage());
              getView().showWeb(data.getBody());
            }
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
