package bkkjatim.techno.com.bkk_andro.News;

/**
 * Created by ari on 6/4/15.
 */
public class NewsDetailModel {
    private String imageUrl;
    private String createdDate;
    private String articleTitle;
    private String companyDescription;
    private String companyAddress;
    private String companyWebsite;
    private String companyProvince;
    private String jobDetail;
    private String jobExpireDate;
    private String companyName;

    public NewsDetailModel(){

    }
    public NewsDetailModel(String imageUrl, String createdDate, String articleTitle, String companyDescription, String companyAddress, String companyWebsite, String companyProvince, String jobDetail, String jobExpireDate, String companyName){
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
        this.articleTitle = articleTitle;
        this.companyDescription = companyDescription;
        this.companyAddress = companyAddress;
        this.companyWebsite = companyWebsite;
        this.companyProvince = companyProvince;
        this.jobDetail = jobDetail;
        this.jobExpireDate = jobExpireDate;
        this.companyName = companyName;

    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyProvince() {
        return companyProvince;
    }

    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince;
    }

    public String getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(String jobDetail) {
        this.jobDetail = jobDetail;
    }

    public String getJobExpireDate() {
        return jobExpireDate;
    }

    public void setJobExpireDate(String jobExpireDate) {
        this.jobExpireDate = jobExpireDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private String department;

}
