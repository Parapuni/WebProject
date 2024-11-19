package cmt.db.jdbc;

import cmt.db.api.MusicHandler;
import cmt.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MusicJdbc implements MusicHandler {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CategoryJdbc categoryJdbc;
    @Autowired
    private ItemJdbc itemJdbc;
    @Autowired
    public MusicJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMusic(Music music) {
        long iid = itemJdbc.addItemReturnPrimaryKey(music);
        /*
        TODO
         */
        categoryJdbc.addItemCategories(iid, music.getCategories());
    }

    @Override
    public void removeMusic(long iid) {
        /*
        TODO
         */
        categoryJdbc.removeItem(iid);
        itemJdbc.removeItem(iid);
    }

    @Override
    public void updateMusic(Music music) {
        itemJdbc.updateItem(music);
        /*
        TODO
         */
    }

    @Override
    public void updateRating(long iid, int rating) {
        itemJdbc.updateRating(iid, rating);
    }

    /*
    TODO
      完成查找
     */
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

    private static final class MusicRowMapper implements RowMapper<Music> {

        @Override
        public Music mapRow(ResultSet resultSet, int i) throws SQLException {
            Music music = new Music();
            /*
            TODO
             */
            return music;
        }
    }
}
