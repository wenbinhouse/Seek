package wb.app.seek.common.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import wb.app.seek.modules.model.ZhihuDailyNews;
import wb.app.seek.modules.model.ZhihuDailyStory;

/**
 * API
 * <p>
 * Created by W.b on 16/9/24.
 */
public interface AppService {

    @GET("{url}")
    Observable<ResponseBody> executeGet(@Url() String url);

    @GET("{url}")
    Observable<ResponseBody> executeGet(@Url() String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<ResponseBody> executePost(@Url() String url, @QueryMap Map<String, String> map);

    /**
     * 获取最新消息
     *
     * @return
     */
    @GET("api/4/news/latest")
    Observable<ZhihuDailyNews> getZhiHuNewsLatest();

    /**
     * 获取过往消息
     * 若果需要查询 11 月 18 日的消息，before 后的数字应为 20131119
     * 知乎日报的生日为 2013 年 5 月 19 日，若 before 后数字小于 20130520 ，只会接收到空消息
     * 输入的今日之后的日期仍然获得今日内容，但是格式不同于最新消息的 JSON 格式
     *
     * @param date
     * @return
     */
    @GET("api/4/news/before/{date}")
    Observable<ZhihuDailyNews> getZhihuNewsByDate(@Path("date") String date);

    @GET("api/4/news/{storyId}")
    Observable<ZhihuDailyStory> getZhihuNewsDetail(@Path("storyId") int storyId);

}
