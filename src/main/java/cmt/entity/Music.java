package cmt.entity;

import java.net.URL;
import java.util.Date;
import java.util.List;

public class Music extends Item {
    private String artists;//创作者
    private String album;//所属专辑
    private String lyrics;//歌词

    public Music() {
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
     * @param artists 创作者
     * @param album 专辑
     * @param lyrics 歌词
     */
    public Music(long iid, String title, Date releaseDate, int[] stars, List<String> categories, double rating, URL coverImagine, String artists, String album, String lyrics) {
        super(iid, title, releaseDate, stars, categories, rating, coverImagine);
        this.artists = artists;
        this.album = album;
        this.lyrics = lyrics;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
