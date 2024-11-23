package cmt.db.jdbc;

import cmt.db.api.MusicHandler;
import cmt.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MusicJdbc implements MusicHandler {

    private final String INSERT_MUSIC = "insert into Music values(?,?,?,?)";
    private final String DELETE_MUSIC = "delete from Music where iid = ?";
    private final String UPDATE_MUSIC = "update Music set `authors` = ?,`publisher` = ?,`introduction` = ?  where iid = ?;";
    private final String SELECT_MUSIC_BY_ID = "select * from Music m natural join item i where m.iid = ?;";
    private final String SELECT_MUSICS = "select * from Music m natural join item i order by i.releaseDate desc limit ? offset ? ;";
    private final String SELECT_MUSICS_BY_CATEGORY = "select * from " +
            "(select m.* from Music m natural join Category_Item ci where ci.name in (?) limit ? offset ?) " +
            "as cm natural join Item i";
    private final String SELECT_MUSICS_BY_TITLE = "select * from Music m natural join Item i where i.title like ? limit ? offset ?";
    private final String SELECT_MUSICS_BY_ARTISTS = "select * from Music m natural join Item i where m.artists like ? limit ? offset ?";
    private final String SELECT_MUSICS_BY_ALBUM = "select * from Music m natural join Item i where m.album like ? limit ? offset ?";
    private final String COUNT_TOTAL = "select COUNT(*) from Music";

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
    public int countTotal() {
        return jdbcTemplate.queryForInt(COUNT_TOTAL);
    }

    @Override
    public void addMusic(Music music) {
        long iid = itemJdbc.addItemReturnPrimaryKey(music);
        jdbcTemplate.update(INSERT_MUSIC, iid, music.getArtists(), music.getLyrics(), music.getAlbum());
        categoryJdbc.addItemCategories(iid, music.getCategories());
    }

    @Override
    public void removeMusic(long iid) {
        jdbcTemplate.update(DELETE_MUSIC, iid);
        categoryJdbc.removeItem(iid);
        itemJdbc.removeItem(iid);
    }

    @Override
    public void updateMusic(Music music) {

        jdbcTemplate.update(UPDATE_MUSIC, music.getArtists(), music.getLyrics(), music.getAlbum(),music.getIid());
        categoryJdbc.updateItemCategories(music);
        itemJdbc.updateItem(music);
    }

    @Override
    public void updateRating(long iid, int rating) {
        itemJdbc.updateRating(iid, rating);
    }


    @Override
    public Music findMusicById(long iid) {
        Music music = jdbcTemplate.queryForObject(SELECT_MUSIC_BY_ID,new MusicRowMapper(),iid);
        categoryJdbc.setCategory(music);
        return music;
    }

    @Override
    public List<Music> findMusics(int offset, int length) {
        List<Music> musics = jdbcTemplate.query(SELECT_MUSICS, new MusicRowMapper(), length ,offset);
        categoryJdbc.setCategory(musics);
        return musics;
    }

    @Override
    public List<Music> findMusicsByCategories(int offset, int length, List<String> NameOfCategories) {
        String categories = String.join(",", NameOfCategories);
        List<Music> musics = jdbcTemplate.query(SELECT_MUSICS_BY_CATEGORY, new MusicRowMapper(), categories, length, offset);
        categoryJdbc.setCategory(musics);
        return musics;
    }

    @Override
    public List<Music> findMusicsByTitle(int offset, int length, String title) {
        List<Music> musics = jdbcTemplate.query(SELECT_MUSICS_BY_TITLE, new MusicRowMapper(), "%" + title + "%", length, offset);
        categoryJdbc.setCategory(musics);
        return musics;
    }

    @Override
    public List<Music> findMusicsByArtists(int offset, int length, String artists) {
        List<Music> musics = jdbcTemplate.query(SELECT_MUSICS_BY_ARTISTS, new MusicRowMapper(), "%" + artists + "%", length, offset);
        categoryJdbc.setCategory(musics);
        return musics;
    }

    @Override
    public List<Music> findMusicsByAlbum(int offset, int length, String Album) {
        List<Music> musics = jdbcTemplate.query(SELECT_MUSICS_BY_ALBUM,new MusicRowMapper(), "%" + Album + "%", length, offset);
        categoryJdbc.setCategory(musics);
        return musics;
    }

    private static final class MusicRowMapper implements RowMapper<Music> {

        @Override
        public Music mapRow(ResultSet resultSet, int i) throws SQLException {
            Music music = new Music();

            music.setIid(resultSet.getLong("iid"));
            music.setTitle(resultSet.getString("title"));
            music.setReleaseDate(resultSet.getDate("releaseDate"));
            music.setStars(resultSet.getString("stars"));
            music.setRating(resultSet.getDouble("rating"));
            music.setCoverImagine(resultSet.getString("coverImagine"));
            music.setArtists(resultSet.getString("artists"));
            music.setLyrics(resultSet.getString("lyrics"));
            music.setAlbum(resultSet.getString("album"));

            return music;
        }
    }
}
