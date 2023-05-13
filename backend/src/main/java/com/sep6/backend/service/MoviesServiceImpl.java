package com.sep6.backend.service;


import com.sep6.backend.models.Movie;
import com.sep6.backend.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoviesServiceImpl implements MoviesService {
    private MoviesRepository repository;

    @Override
    public Movie createMovie(Movie movie) {
        return repository.createMovie(movie);
    }
}
