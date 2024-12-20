package cmt.db.jdbc;

import cmt.db.api.MovieHandler;
import cmt.entity.Movie;
import cmt.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class MovieJdbc implements MovieHandler {
    private final String COUNT_TOTAL = "select COUNT(*) from Movie";
    private final String COUNT_BY_CATEGORY = "select COUNT(*) from " +
            "(select m.* from Movie m natural join Category_Item ci where ci.name in (?)) " +
            "as cm natural join Item i";
    private final String COUNT_BY_TITLE = "select COUNT(*) from Movie m natural join Item i where i.title like ? ";
    private final String COUNT_BY_DIRECTOR = "select COUNT(*) from Movie m natural join Item i where m.director like ? ";
    private final String COUNT_BY_CAST = "select COUNT(*) from Movie m natural join Item i where m.cast like ? ";
    private final String COUNT_BY_WRITERS = "select COUNT(*) from Movie m natural join Item i where m.writers like ? ";
    private final String COUNT_BY_YEAR = "select count(*) from Movie m natural join Item i where Year(i.releaseDate) = ? ";
    private final String INSERT_MOVIE = "insert into Movie values(?,?,?,?,?)";
    private final String DELETE_MOVIE = "delete from Movie where iid = ?";
    private final String SELECT_MOVIE_BY_ID = "select * from Movie m natural join Item i where m.iid = ?";
    private final String SELECT_MOVIES = "select * from Movie m natural join Item i order by i.releaseDate desc limit ? offset ? ";
    private final String SELECT_MOVIES_BY_CATEGORY = "select * from " +
            "(select m.* from Movie m natural join Category_Item ci where ci.name in (?) limit ? offset ?) " +
            "as cm natural join Item i";
    private final String SELECT_MOVIES_BY_TITLE = "select * from Movie m natural join Item i where i.title like ? limit ? offset ?";
    private final String SELECT_MOVIES_BY_DIRECTOR = "select * from Movie m natural join Item i where m.director like ? limit ? offset ?";
    private final String SELECT_MOVIES_BY_CAST = "select * from Movie m natural join Item i where m.cast like ? limit ? offset ?";
    private final String SELECT_MOVIES_BY_WRITERS = "select * from Movie m natural join Item i where m.writers like ? limit ? offset ?";
    private final String SELECT_MOVIES_BY_YEAR = "select * from Movie m natural join Item i where Year(i.releaseDate) = ? ";
    private final String UPDATE_MOVIE = "update Movie set director = ?, writers = ?, cast = ?, introduction = ? where iid = ?";
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
    public int countTotal() {
        return jdbcTemplate.queryForInt(COUNT_TOTAL);
    }

    @Override
    public void addMovie(Movie movie) {
        long iid = itemJdbc.addItemReturnPrimaryKey(movie);
        jdbcTemplate.update(INSERT_MOVIE, iid, movie.getDirector(), movie.getWriters(), movie.getCast(), movie.getIntroduction());
        categoryJdbc.addItemCategories(iid, movie.getCategories());
    }

    @Override
    public void removeMovie(long iid) {
        jdbcTemplate.update(DELETE_MOVIE, iid);
        categoryJdbc.removeItem(iid);
        itemJdbc.removeItem(iid);
    }

    @Override
    public void updateMovie(Movie movie) {
        itemJdbc.updateItem(movie);
        jdbcTemplate.update(UPDATE_MOVIE, movie.getDirector(), movie.getWriters(), movie.getCast(), movie.getIntroduction(), movie.getIid());
        categoryJdbc.updateItemCategories(movie);
    }

    @Override
    public void updateRating(long iid, int star) {
        itemJdbc.updateRating(iid, star);
    }

    @Override
    public Movie findMovieById(long iid) {
        Movie movie = null;
        try {
            movie = jdbcTemplate.queryForObject(SELECT_MOVIE_BY_ID, new MovieRowMapper(), iid);
            categoryJdbc.setCategory(movie);
        }catch (DataAccessException dae){
        }finally {
            return movie;
        }
    }

    @Override
    public List<Movie> findMovies(int offset, int length) {
        List<Movie> movies = jdbcTemplate.query(SELECT_MOVIES, new MovieRowMapper(), length, offset);
        categoryJdbc.setCategory(movies);
        return movies;
    }

    @Override
    public List<Movie> findMoviesByCategories(int offset, int length, List<String> NameOfCategories) {
        String categories = String.join(",", NameOfCategories);
        List<Movie> movies = jdbcTemplate.query(SELECT_MOVIES_BY_CATEGORY, new MovieRowMapper(), categories, length, offset);
        categoryJdbc.setCategory(movies);
        return movies;
    }

    @Override
    public int countByCategories(List<String> NameOfCategories) {
        String categories = String.join(",", NameOfCategories);
        return jdbcTemplate.queryForInt(COUNT_BY_CATEGORY,categories);
    }

    @Override
    public List<Movie> findMoviesByTitle(int offset, int length, String title) {
        List<Movie> movies = jdbcTemplate.query(SELECT_MOVIES_BY_TITLE, new MovieRowMapper(), "%" + title + "%", length, offset);
        categoryJdbc.setCategory(movies);
        return movies;
    }

    @Override
    public int countByTitle(String title) {
        return jdbcTemplate.queryForInt(COUNT_BY_TITLE,"%" + title + "%");
    }

    @Override
    public List<Movie> findMoviesByDirector(int offset, int length, String director) {
        List<Movie> movies = jdbcTemplate.query(SELECT_MOVIES_BY_DIRECTOR, new MovieRowMapper(), "%" + director + "%", length, offset);
        categoryJdbc.setCategory(movies);
        return movies;
    }

    @Override
    public int countByDirector(String director) {
        return jdbcTemplate.queryForInt(COUNT_BY_DIRECTOR,"%" + director + "%");
    }

    @Override
    public List<Movie> findMoviesByCast(int offset, int length, String cast) {
        List<Movie> movies = jdbcTemplate.query(SELECT_MOVIES_BY_CAST, new MovieRowMapper(), "%" + cast + "%", length, offset);
        categoryJdbc.setCategory(movies);
        return movies;
    }

    @Override
    public int countByCast(String cast) {
        return jdbcTemplate.queryForInt(COUNT_BY_CAST,"%" + cast + "%");
    }

    @Override
    public List<Movie> findMoviesByWriters(int offset, int length, String writers) {
        List<Movie> movies = jdbcTemplate.query(SELECT_MOVIES_BY_WRITERS, new MovieRowMapper(), "%" + writers + "%", length, offset);
        categoryJdbc.setCategory(movies);
        return movies;
    }

    @Override
    public int countByWriters(String writers) {
        return jdbcTemplate.queryForInt(COUNT_BY_WRITERS,"%" + writers + "%");
    }

    @Override
    public List<Movie> findMoviesByYear(int year) {
        List<Movie> movies = jdbcTemplate.query(SELECT_MOVIES_BY_YEAR,new MovieRowMapper(),year);
        categoryJdbc.setCategory(movies);
        return movies;
    }

    @Override
    public int countByYear(int year) {
        return jdbcTemplate.queryForInt(COUNT_BY_YEAR,year);
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
            movie.setCoverImagine(resultSet.getString("coverImagine"));
            movie.setDirector(resultSet.getString("director"));
            movie.setWriters(resultSet.getString("writers"));
            movie.setCast(resultSet.getString("cast"));
            movie.setIntroduction(resultSet.getString("introduction"));
            return movie;
        }
    }
}
