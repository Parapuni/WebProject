package cmt.db.jdbc;

import cmt.db.api.MusicHandler;
import cmt.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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

        private final String INSERT_MUSIC = "insert into Music(`iid`,`album`,`artists`,`lyrics`) values(?,?,?,?);";


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_MUSIC, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, music.getIid());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getNumber());
            ps.setDate(7, user.getBirthday());
            ps.setString(8, user.getAvatar().toString());
            return ps;
        }, keyHolder);
        user.setUid((Long) keyHolder.getKey());
        return user;
    }


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
