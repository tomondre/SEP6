package com.sep6.backend.repository;

import com.sep6.backend.models.Person;

import java.util.List;
import java.util.Optional;

public interface DirectorsRepository {
    List<Person> getDirectors();
    List<Person> getDirectorsBySearch(String search);
    Optional<Person> getDirectorsById(int id);
}
