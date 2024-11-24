package cmt.entity;

import com.sun.istack.internal.NotNull;

import java.sql.Date;
import java.util.List;

/**
 * 书影音作品项目类
 */
public abstract class Item {
    @NotNull
    private long iid;//Item的id
    @NotNull
    private String title;//标题或者名称
    private Date releaseDate;//发行日期
    private int[] stars;//评星级人数
    private List<String> categories;//作品分类，存在 Item-Category表中
    private double rating;//电影评分，评星按人数加权平均计算得到
    private String coverImagine;//封面，存储在resource/imagines中，数据库中只存储其文件名(也是其编号)
    private int totalRating;
    public Item() {
        this.stars = new int[5];
        totalRating = 1;
    }

    /**
     * 构造函数
     *
     * @param iid          id
     * @param title        标题
     * @param releaseDate  发行日期
     * @param stars        评分人数
     * @param categories   分类
     * @param rating        评分
     * @param coverImagine 封面
     */
    public Item(long iid, String title, Date releaseDate, int[] stars, List<String> categories, double rating, String coverImagine) {
        this.iid = iid;
        this.title = title;
        this.releaseDate = releaseDate;
        this.stars = stars;
        this.categories = categories;
        this.rating = rating;
        this.coverImagine = coverImagine;
        totalRating = 1;
    }

    public int getTotalRating() {
       return totalRating==0?1:totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int[] getStars() {
        return stars;
    }

    public void setStars(int[] stars) {
        this.stars = stars;
    }

    public void setStars(String s) {
        totalRating = 0;
        String[] ss = s.substring(1, s.length() - 1).split(",");
        for (int i = 0; i < 5; i++) {
            this.stars[i] = Integer.parseInt(ss[i].trim());
            totalRating += this.stars[i];
        }

    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCoverImagine() {
        return coverImagine;
    }

    public void setCoverImagine(String coverImagine) {
        this.coverImagine = coverImagine;
    }
}
