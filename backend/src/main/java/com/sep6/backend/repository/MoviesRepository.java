package com.sep6.backend.repository;

import com.sep6.backend.models.Movie;

import java.util.List;

public interface MoviesRepository {
    Movie createMovie(Movie movie);
    List<Movie> getMovies();
    List<Movie> getMoviesBySearch(String search);
    List<Movie> getMoviesByGenreId(int genreId);
    Movie getMovieById(int id);
    List<Movie> getLatestMovies(int actualLimit);
}
