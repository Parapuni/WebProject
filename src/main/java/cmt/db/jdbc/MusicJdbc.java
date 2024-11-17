package cmt.db.jdbc;

import cmt.db.api.MusicHandler;
import cmt.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MusicJdbc implements MusicHandler {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MusicJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMusic(Music music) {

    }

    @Override
    public void removeMusic(long iid) {

    }

    @Override
    public void updateMusic(Music music) {

    }

    @Override
    public void updateRating(long iid, int rating) {

    }

    @Override
    public Music findMusicById(long iid) {
        return null;
    }

    @Override
    public List<Music> findMusics(int offset, int length) {
        return null;
    }

    @Override
    public List<Music> findMusicsByCategories(int offset, int length, List<String> NameOfCategories) {
        return null;
    }

    @Override
    public List<Music> findMusicsByTitle(int offset, int length, String title) {
        return null;
    }

    @Override
    public List<Music> findMusicsByArtists(int offset, int length, String artists) {
        return null;
    }

    @Override
    public List<Music> findMusicsByAlbum(int offset, int length, String Album) {
        return null;
    }
}
