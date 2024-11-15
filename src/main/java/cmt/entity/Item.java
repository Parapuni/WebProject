package cmt.entity;

import com.sun.istack.internal.NotNull;

import java.net.URL;
import java.util.Date;

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
    private String[] categories;//作品分类，存在 Item-Category表中
    private double score;//电影评分，评星按人数加权平均计算得到
    private URL coverImagine;//封面，存储在服务器中，数据库中只存储其URL

    public Item() {
    }

    /**
     * 构造函数
     *
     * @param iid          id
     * @param title        标题
     * @param releaseDate  发行日期
     * @param stars        评分人数
     * @param categories   分类
     * @param score        评分
     * @param coverImagine 封面
     */
    public Item(long iid, String title, Date releaseDate, int[] stars, String[] categories, double score, URL coverImagine) {
        this.iid = iid;
        this.title = title;
        this.releaseDate = releaseDate;
        this.stars = stars;
        this.categories = categories;
        this.score = score;
        this.coverImagine = coverImagine;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public URL getCoverImagine() {
        return coverImagine;
    }

    public void setCoverImagine(URL coverImagine) {
        this.coverImagine = coverImagine;
    }
}
