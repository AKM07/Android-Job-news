package bkkjatim.techno.com.bkk_andro.News;

/**
 * Created by ari on 6/3/15.
 */
public class NewsModel {


    private String id;
    private String imageUrl;
    private String articleTitle;
    private String articleDate;

    public NewsModel(){

    }
    public NewsModel(String id,String imageUrl, String articleTitle, String articleDate){
        this.id = id;
        this.imageUrl = imageUrl;
        this.articleTitle = articleTitle;
        this.articleDate = articleDate;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }
}
