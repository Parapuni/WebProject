package cmt.db.api;


import cmt.entity.Movie;

import java.util.List;

/**
 * Movie数据操作接口
 */
public interface MovieHandler {
    void addMovie(Movie movie);

    void removeMovie(long iid);

    void updateMovie(Movie movie);

    void updateRating(long iid, int rating);

    Movie findMovieById(long iid);

    List<Movie> findMovies(int offset, int length);

    List<Movie> findMoviesByCategories(int offset, int length, List<String> NameOfCategories);

    List<Movie> findMoviesByTitle(int offset, int length, String title);

    List<Movie> findMoviesByDirector(int offset, int length, String director);

    List<Movie> findMoviesByCast(int offset, int length, String cast);

    List<Movie> findMoviesByWriters(int offset, int length, String writers);
}
