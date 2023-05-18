package com.sep6.backend.service;


import com.sep6.backend.projections.MovieBasicInfoProjection;
import com.sep6.backend.models.Movie;
import com.sep6.backend.repository.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MoviesServiceImpl implements MoviesService {
    private MoviesRepository repository;

    @Override
    public Movie createMovie(Movie movie) {
        return repository.createMovie(movie);
    }

    @Override
    public List<MovieBasicInfoProjection> getMovies() {
        return repository.getMovies();
    }

    @Override
    public List<MovieBasicInfoProjection> getMoviesBySearch(String search) {
        return repository.getMoviesBySearch(search);
    }

    @Override
    public List<MovieBasicInfoProjection> getMoviesByGenreId(int genreId) {
        return repository.getMoviesByGenreId(genreId);
    }

    @Override
    public Movie getMovieById(int id) {
        return repository.getMovieById(id);
    }

    @Override
    public List<MovieBasicInfoProjection> getLatestMovies(int actualLimit) {
        return repository.getLatestMovies(actualLimit);
    }

    @Override
    public List<MovieBasicInfoProjection> getPaginatedMovies(int pageInt) {
        return repository.getPaginatedMovies(pageInt);
    }
}
