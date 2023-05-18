package com.sep6.backend.repository;

import com.sep6.backend.models.Person;

import java.util.List;
import java.util.Optional;

public interface ActorsRepository {
    List<Person> getActors();
    List<Person> getActorsBySearch(String search);
    Optional<Person> getActorById(int id);
    Person save(Person person);

    Optional<Person> findById(int id);
}
