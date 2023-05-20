package com.sep6.backend.service;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;

import java.util.List;
import java.util.Optional;

public interface PeopleService {
    List<PersonProjection> getPeopleBySearch(String search);
    List<PersonProjection> getPeople();
    PersonMoviesProjection getPersonById(int id);
    List<PersonProjection> getPaginatedPeople(int page);
}
