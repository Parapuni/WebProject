package cmt.db.jdbc;

import cmt.db.api.MovieHandler;
import cmt.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MovieJdbc implements MovieHandler {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMovie(Movie movie) {

    }

    @Override
    public void removeMovie(long iid) {

    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public void updateRating(long iid, int rating) {

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
}
