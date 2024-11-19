package cmt.db.jdbc;

import cmt.db.api.MovieHandler;
import cmt.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;


/*
TODO
  需要同时维护Item 和 Movie 和 Category_Item
 */
@Repository
public class MovieJdbc implements MovieHandler {
    private final String INSERT_ITEM = "insert into Item(`title`,`releaseDate`,`stars`,`rating`,`coverimagine`) values(?,?,?,?,?)";
    private final String INSERT_MOVIE = "insert into Movie values(?,?,?,?,?)";
    private final String DELETE_ITEM = "delete from Item where iid = ?";
    private final String DELETE_MOVIE = "delete from Movie where iid = ?";
    private final String SELECT_STARS = "select stars from Item where iid = ?";
    private final String UPDATE_STARS = "update Item set stars = ?,rating =? where iid = ?";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CategoryJdbc categoryJdbc;
    @Autowired
    public MovieJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMovie(Movie movie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ITEM, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, movie.getTitle());
            ps.setDate(2, movie.getReleaseDate());
            ps.setString(3, Arrays.toString(movie.getStars()));
            ps.setDouble(4, movie.getRating());
            ps.setString(5, movie.getCoverImagine().toString());
            return ps;
        }, keyHolder);
        jdbcTemplate.update(INSERT_MOVIE, keyHolder.getKey().longValue(), movie.getDirector(), movie.getWriters(), movie.getCast(), movie.getIntoduction());
        categoryJdbc.addItemCategories(keyHolder.getKey().longValue(), movie.getCategories());
    }

    @Override
    public void removeMovie(long iid) {
        //由于Movie中的iid是参照Item中iid的外键，不能先删除Item中的记录
        jdbcTemplate.update(DELETE_MOVIE, iid);
        categoryJdbc.removeItem(iid);
        jdbcTemplate.update(DELETE_ITEM, iid);
    }

    @Override
    public void updateMovie(Movie movie) {

    }

    /**
     * 更新评分
     *
     * @param iid
     * @param star
     */
    @Override
    public void updateRating(long iid, int star) {
        String sStars = jdbcTemplate.queryForObject(SELECT_STARS, String.class, iid);
        sStars.substring(1, sStars.length() - 1);
        int[] stars = new int[5];
        String[] s = sStars.split(",");
        double rating = 0.0;
        long sum = 0;
        for (int i = 0; i < 5; i++) {
            stars[i] = Integer.parseInt(s[i]);
            if (i == star)
                stars[i]++;
            sum += stars[i];
            rating += (i + 1) * stars[i];
        }
        rating = rating / sum;
        jdbcTemplate.update(UPDATE_STARS, Arrays.toString(stars), rating);
    }

    @Override
    public Movie findMovieById(long iid) {
        return null;
    }

    @Override
    public List<Movie> findMovies(int offset, int length) {
        return null;
    }

    @Override
    public List<Movie> findMoviesByCategories(int offset, int length, List<String> NameOfCategories) {
        return null;
    }

    @Override
    public List<Movie> findMoviesByTitle(int offset, int length, String title) {
        return null;
    }

    @Override
    public List<Movie> findMoviesByDirector(int offset, int length, String director) {
        return null;
    }

    @Override
    public List<Movie> findMoviesByCast(int offset, int length, String cast) {
        return null;
    }

    @Override
    public List<Movie> findMoviesByWriters(int offset, int length, String writers) {
        return null;
    }

    private static final class MovieRowMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
            Movie movie = new Movie();
            movie.setIid(resultSet.getLong("iid"));
            movie.setTitle(resultSet.getString("title"));
            movie.setReleaseDate(resultSet.getDate("releaseDate"));
            movie.setStars(resultSet.getString("stars"));
            movie.setRating(resultSet.getDouble("rating"));
            movie.setCoverImagine(resultSet.getURL("coverImagine"));
            movie.setDirector(resultSet.getString("directors"));
            movie.setWriters(resultSet.getString("writers"));
            movie.setCast(resultSet.getString("cast"));
            movie.setIntoduction(resultSet.getString("introduction"));
            return movie;
        }
    }
}
