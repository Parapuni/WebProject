package cmt.entity;

import java.net.URL;
import java.util.Date;
import java.util.List;

public class Movie extends Item {
    private String director;//导演
    private String writers;//编剧
    private String cast;//演员
    private String intoduction;//简介

    public Movie() {
        super();
    }

    /**
     * 构造函数
     *
     * @param iid          id
     * @param title        标题
     * @param releaseDate  发行日期
     * @param stars        评分人数
     * @param categories   分类
     * @param rating       评分
     * @param coverImagine 封面
     * @param director 导演
     * @param writers 编剧
     * @param cast 演员阵容
     * @param intoduction 简介
     */
    public Movie(long iid, String title, Date releaseDate, int[] stars, List<String> categories, double rating, URL coverImagine, String director, String writers, String cast, String intoduction) {
        super(iid, title, releaseDate, stars, categories, rating, coverImagine);
        this.director = director;
        this.writers = writers;
        this.cast = cast;
        this.intoduction = intoduction;
    }
}
