package cmt.db.api;


import cmt.entity.Music;

import java.util.List;

/**
 * Music数据操作接口
 */
public interface MusicHandler {
    int countTotal();
    void addMusic(Music music);

    void removeMusic(long iid);

    void updateMusic(Music music);

    void updateRating(long iid, int rating);

    Music findMusicById(long iid);

    List<Music> findMusics(int offset, int length);

    List<Music> findMusicsByCategories(int offset, int length, List<String> NameOfCategories);

    List<Music> findMusicsByTitle(int offset, int length, String title);

    List<Music> findMusicsByArtists(int offset, int length, String artists);

    List<Music> findMusicsByAlbum(int offset, int length, String Album);
}
