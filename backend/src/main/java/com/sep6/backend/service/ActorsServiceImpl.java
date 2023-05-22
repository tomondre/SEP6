package com.sep6.backend.service;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.repository.ActorsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ActorsServiceImpl implements ActorsService {
    private ActorsRepository repository;

    @Override
    public List<PersonProjection> getActorsBySearch(String search) {
        log.info("Searching for actors by search term: {}", search);
        return repository.getActorsBySearch(search);
    }

    @Override
    public List<PersonProjection> getActors() {
        log.info("Getting all actors");
        return repository.getActors();
    }

    @Override
    public PersonMoviesProjection getActorById(int id) {
        log.info("Getting actor by ID: {}", id);
        return repository.getActorById(id).orElseThrow();
    }
}
