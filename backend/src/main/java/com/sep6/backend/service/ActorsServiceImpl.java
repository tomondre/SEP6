package com.sep6.backend.service;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.repository.ActorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActorsServiceImpl implements ActorsService {
    private ActorsRepository repository;

    @Override
    public List<PersonProjection> getActorsBySearch(String search) {
        return repository.getActorsBySearch(search);
    }

    @Override
    public List<PersonProjection> getActors() {
        return repository.getActors();
    }

    @Override
    public PersonMoviesProjection getActorById(int id) {
        return repository.getActorById(id).orElseThrow();
    }
}
