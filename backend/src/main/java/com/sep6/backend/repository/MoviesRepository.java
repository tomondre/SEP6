package com.sep6.backend.repository;

import com.sep6.backend.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository {
    Movie createMovie(Movie movie);
    List<Movie> getMovies();
    List<Movie> getMoviesBySearch(String search);
    List<Movie> getMoviesByGenreId(int genreId);
    Optional<Movie> getMovieById(int id);
    List<Movie> getLatestMovies(int actualLimit);
    List<Movie> getPaginatedMovies(int pageInt);
    Movie updateMovieRatingById(int movieId, double rating);
}
