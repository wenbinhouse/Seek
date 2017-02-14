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
              getView().showWeb(convertZhihuContent(data.getBody()));
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

  private String convertZhihuContent(String preResult) {

    preResult = preResult.replace("<div class=\"img-place-holder\">", "");
    preResult = preResult.replace("<div class=\"headline\">", "");

    // 在api中，css的地址是以一个数组的形式给出，这里需要设置
    // in fact,in api,css addresses are given as an array
    // api中还有js的部分，这里不再解析js
    // javascript is included,but here I don't use it
    // 不再选择加载网络css，而是加载本地assets文件夹中的css
    // use the css file from local assets folder,not from network
    String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";


    // 根据主题的不同确定不同的加载内容
    // load content judging by different theme
    String theme = "<body className=\"\" onload=\"onLoaded()\">";
//    if ((context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
//        == Configuration.UI_MODE_NIGHT_YES){
//      theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
//    }

    return new StringBuilder()
        .append("<!DOCTYPE html>\n")
        .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
        .append("<head>\n")
        .append("\t<meta charset=\"utf-8\" />")
        .append(css)
        .append("\n</head>\n")
        .append(theme)
        .append(preResult)
        .append("</body></html>").toString();
  }
}
