package com.sep6.backend.repository;

import com.sep6.backend.projections.MovieProjection;
import com.sep6.backend.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository {
    Movie createMovie(Movie movie);
    List<MovieProjection> getMovies();
    List<MovieProjection> getMoviesBySearch(String search);
    List<MovieProjection> getMoviesByGenreId(int genreId);
    Optional<Movie> getMovieById(int id);
    List<MovieProjection> getLatestMovies(int actualLimit);
    List<MovieProjection> getPaginatedMovies(int pageInt);
    Movie updateMovieRatingById(int movieId, double rating);
    Movie getMovieReferenceById(int id);
}
