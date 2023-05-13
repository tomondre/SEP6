package com.sep6.backend.service;


import com.sep6.backend.models.Movie;
import com.sep6.backend.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoviesServiceImpl implements MoviesService {
    private MoviesRepository repository;

    @Autowired
    public MoviesServiceImpl(MoviesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movie createMovie(Movie movie) {
        return repository.createMovie(movie);
    }
}
