package com.sep6.backend.repository;

import com.sep6.backend.models.Person;

import java.util.List;

public interface ActorsRepository {
    List<Person> getActors();
    List<Person> getActorsBySearch(String search);
    Person getActorById(int id);
}
