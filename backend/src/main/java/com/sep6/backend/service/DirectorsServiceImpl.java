package com.sep6.backend.service;


import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.repository.DirectorsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DirectorsServiceImpl implements DirectorsService {
    private DirectorsRepository repository;

    @Override
    public List<PersonProjection> getDirectorsBySearch(String search) {
        log.info("Searching for directors by search term: {}", search);
        return repository.getDirectorsBySearch(search);
    }

    @Override
    public List<PersonProjection> getDirectors() {
        log.info("Getting all directors");
        return repository.getDirectors();
    }

    @Override
    public PersonMoviesProjection getDirectorById(int id) {
        log.info("Getting director by ID: {}", id);
        return repository.getDirectorsById(id).orElseThrow();
    }
}
