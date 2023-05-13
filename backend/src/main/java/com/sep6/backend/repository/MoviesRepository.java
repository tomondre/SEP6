package com.sep6.backend.repository;

import com.sep6.backend.models.Movie;

public interface MoviesRepository {
    Movie createMovie(Movie movie);
}
