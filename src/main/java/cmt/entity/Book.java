package cmt.entity;

import java.net.URL;
import java.util.Date;
import java.util.List;

public class Book extends Item {
     private String authors;//作者
     private String publisher;//出版社
     private String introduction;//简介

    public Book() {
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
     * @param authors      作者
     * @param publisher    出版商
     * @param introduction 简介
     */
    public Book(long iid, String title, Date releaseDate, int[] stars, List<String> categories, double rating, URL coverImagine, String authors, String publisher, String introduction) {
        super(iid, title, releaseDate, stars, categories, rating, coverImagine);
        this.authors = authors;
        this.publisher = publisher;
        this.introduction = introduction;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
     }
}
