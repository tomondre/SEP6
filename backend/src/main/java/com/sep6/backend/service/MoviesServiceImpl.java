package com.sep6.backend.service;


import com.sep6.backend.projections.MovieProjection;
import com.sep6.backend.models.Movie;
import com.sep6.backend.repository.MoviesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MoviesServiceImpl implements MoviesService {
    private MoviesRepository repository;

    @Override
    public Movie createMovie(Movie movie)
    {
        log.info("Saving movie: {}", movie);
        return repository.createMovie(movie);
    }

    @Override
    public List<MovieProjection> getMovies() {
        log.info("Getting all movies");
        return repository.getMovies();
    }

    @Override
    public List<MovieProjection> getMoviesBySearch(String search) {
        log.info("Searching for movies by search term: {}", search);
        return repository.getMoviesBySearch(search);
    }

    @Override
    public List<MovieProjection> getMoviesByGenreId(int genreId) {
        log.info("Getting movies by genre ID: {}", genreId);
        return repository.getMoviesByGenreId(genreId);
    }

    @Override
    public Movie getMovieById(int id) {
        log.info("Getting movie by ID: {}", id);
        return repository.getMovieById(id).orElseThrow();
    }

    @Override
    public List<MovieProjection> getLatestMovies(int actualLimit) {
        log.info("Getting latest movies");
        return repository.getLatestMovies(actualLimit);
    }

    @Override
    public List<MovieProjection> getPaginatedMovies(int pageInt) {
        log.info("Getting paginated movies");
        return repository.getPaginatedMovies(pageInt);
    }
}
