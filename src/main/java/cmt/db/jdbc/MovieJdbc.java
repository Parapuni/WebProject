package cmt.db.jdbc;

import cmt.db.api.MovieHandler;
import cmt.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/*
TODO
  需要同时维护Item 和 Movie 和 Category_Item
 */
@Repository
public class MovieJdbc implements MovieHandler {

    private final String INSERT_MOVIE = "insert into Movie values(?,?,?,?,?)";

    private final String DELETE_MOVIE = "delete from Movie where iid = ?";

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CategoryJdbc categoryJdbc;
    @Autowired
    private ItemJdbc itemJdbc;

    @Autowired
    public MovieJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMovie(Movie movie) {
        long iid = itemJdbc.addItemReturnPrimaryKey(movie);
        jdbcTemplate.update(INSERT_MOVIE, iid, movie.getDirector(), movie.getWriters(), movie.getCast(), movie.getIntoduction());
        categoryJdbc.addItemCategories(iid, movie.getCategories());
    }

    @Override
    public void removeMovie(long iid) {
        //由于Movie中的iid是参照Item中iid的外键，不能先删除Item中的记录
        jdbcTemplate.update(DELETE_MOVIE, iid);
        categoryJdbc.removeItem(iid);
        itemJdbc.removeItem(iid);
    }

    @Override
    public void updateMovie(Movie movie) {
        itemJdbc.updateItem(movie);
        /*
        TODO
         */
    }

    /**
     * 更新评分
     *
     * @param iid
     * @param star
     */
    @Override
    public void updateRating(long iid, int star) {
        itemJdbc.updateRating(iid, star);
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
