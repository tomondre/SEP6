package com.sep6.backend.repository;

import com.sep6.backend.projections.MovieBasicInfoProjection;
import com.sep6.backend.models.Movie;

import java.util.List;

public interface MoviesRepository {
    Movie createMovie(Movie movie);
    List<MovieBasicInfoProjection> getMovies();
    List<MovieBasicInfoProjection> getMoviesBySearch(String search);
    List<MovieBasicInfoProjection> getMoviesByGenreId(int genreId);
    Movie getMovieById(int id);
    List<MovieBasicInfoProjection> getLatestMovies(int actualLimit);
    List<MovieBasicInfoProjection> getPaginatedMovies(int pageInt);
    Movie updateMovieRatingById(int movieId, double rating);
    Movie getMovieReferenceById(int id);
}
