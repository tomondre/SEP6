package com.sep6.backend.service;

import com.sep6.backend.models.Person;

import java.util.List;

public interface DirectorsService {
    List<Person> getDirectorsBySearch(String search);
    List<Person> getDirectors();
}
