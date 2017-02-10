package wb.app.seek.common.http;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
import wb.app.seek.model.ZhihuDailyNews;
import wb.app.seek.model.ZhihuDailyStory;

/**
 * Created by W.b on 16/9/24.
 */
public interface AppService {

  @GET("{url}")
  Observable<ResponseBody> executeGet(@Url() String url);

  @GET("{url}")
  Observable<ResponseBody> executeGet(@Url() String url, @QueryMap Map<String, String> map);

  @POST("{url}")
  Observable<ResponseBody> executePost(@Url() String url, @QueryMap Map<String, String> map);

  @GET("2/friendships/friends.json")
  Observable<ResponseBody> getFriends(@Query("uid") String uid);

  @GET("2/friendships/followers.json")
  Observable<ResponseBody> getFollowers(@Query("uid") String uid);

  @GET("api/4/news/latest")
  Observable<ZhihuDailyNews> getZhiHuNewsLatest();

  @GET("api/4/news/{storyId}")
  Observable<ZhihuDailyStory> getZhihuNewsDetail(@Path("storyId") int storyId);
}
