package com.sep6.backend.service;


import com.sep6.backend.models.Movie;
import com.sep6.backend.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoviesServiceImpl implements MoviesService {
    private MoviesRepository repository;

    @Override
    public Movie createMovie(Movie movie) {
        return repository.createMovie(movie);
    }

    @Override
    public List<Movie> getMovies() {
        return repository.getMovies();
    }

    @Override
    public List<Movie> getMoviesBySearch(String search) {
        return repository.getMoviesBySearch(search);
    }

    @Override
    public List<Movie> getMoviesByGenreId(int genreId) {
        return repository.getMoviesByGenreId(genreId);
    }

    @Override
    public Movie getMovieById(int id) {
        return repository.getMovieById(id);
    }

    @Override
    public List<Movie> getLatestMovies(int actualLimit) {
        return repository.getLatestMovies(actualLimit);
    }
}
