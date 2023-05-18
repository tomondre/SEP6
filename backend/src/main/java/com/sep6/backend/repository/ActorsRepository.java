package com.sep6.backend.repository;

import com.sep6.backend.models.Person;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;

import java.util.List;
import java.util.Optional;

public interface ActorsRepository {
    List<PersonProjection> getActors();
    List<PersonProjection> getActorsBySearch(String search);
    Person save(Person person);
    Optional<PersonMoviesProjection> getActorById(int id);
}
