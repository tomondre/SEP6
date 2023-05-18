package com.sep6.backend.service;

import com.sep6.backend.projections.MovieBasicInfoProjection;
import com.sep6.backend.models.Movie;

import java.util.List;

public interface MoviesService {
    Movie createMovie(Movie movie);
    List<MovieBasicInfoProjection> getMovies();
    List<MovieBasicInfoProjection> getMoviesBySearch(String search);
    List<MovieBasicInfoProjection> getMoviesByGenreId(int genreId);
    Movie getMovieById(int id);
    List<MovieBasicInfoProjection> getLatestMovies(int actualLimit);
    List<MovieBasicInfoProjection> getPaginatedMovies(int pageInt);
}
