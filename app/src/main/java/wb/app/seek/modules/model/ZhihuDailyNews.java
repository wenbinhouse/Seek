package wb.app.seek.modules.model;

import java.util.List;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyNews {

  private String date;
  private List<ZhihuDailyStory> stories;
  private List<ZhihuDailyStory> top_stories;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public List<ZhihuDailyStory> getStories() {
    return stories;
  }

  public void setStories(List<ZhihuDailyStory> stories) {
    this.stories = stories;
  }

  public List<ZhihuDailyStory> getTop_stories() {
    return top_stories;
  }

  public void setTop_stories(List<ZhihuDailyStory> top_stories) {
    this.top_stories = top_stories;
  }
}
