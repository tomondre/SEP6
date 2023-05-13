package com.sep6.backend.service;

import com.sep6.backend.models.Person;

import java.util.List;

public interface ActorsService {
    List<Person> getActorsBySearch(String search);
    List<Person> getActors();
    Person getActorById(int id);
}
