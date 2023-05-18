package com.sep6.backend.service;


import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.repository.DirectorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DirectorsServiceImpl implements DirectorsService {
    private DirectorsRepository repository;

    @Override
    public List<PersonProjection> getDirectorsBySearch(String search) {
        return repository.getDirectorsBySearch(search);
    }

    @Override
    public List<PersonProjection> getDirectors() {
        return repository.getDirectors();
    }

    @Override
    public PersonMoviesProjection getDirectorsById(int id) {
        return repository.getDirectorsById(id).orElseThrow();
    }
}
