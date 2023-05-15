package com.sep6.backend.repository;

import com.sep6.backend.models.Person;

import java.util.List;

public interface DirectorsRepository {
    List<Person> getDirectors();
    List<Person> getDirectorsBySearch(String search);
    Person getDirectorsById(int id);
}
