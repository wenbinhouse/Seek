package wb.app.seek.model;

import java.util.List;

/**
 * Created by W.b on 2017/2/9.
 */
public class ZhihuDailyStory {

  private int type;
  private int id;
  private String ga_prefix;
  private String title;
  private List<String> images;
  private String image;
  private String body;
  private String image_source;
  private String share_url;
  private List<?> js;
  private List<String> css;

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getGa_prefix() {
    return ga_prefix;
  }

  public void setGa_prefix(String ga_prefix) {
    this.ga_prefix = ga_prefix;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getImages() {
    return images;
  }

  public void setImages(List<String> images) {
    this.images = images;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getImage_source() {
    return image_source;
  }

  public void setImage_source(String image_source) {
    this.image_source = image_source;
  }

  public String getShare_url() {
    return share_url;
  }

  public void setShare_url(String share_url) {
    this.share_url = share_url;
  }

  public List<?> getJs() {
    return js;
  }

  public void setJs(List<?> js) {
    this.js = js;
  }

  public List<String> getCss() {
    return css;
  }

  public void setCss(List<String> css) {
    this.css = css;
  }
}
