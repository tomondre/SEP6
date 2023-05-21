package com.sep6.backend.repository;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository {
    List<PersonProjection> getPeopleBySearch(String search);
    List<PersonProjection> getPeople();
    Optional<PersonMoviesProjection> getPersonById(int id);
    List<PersonProjection> getPaginatedPeople(int page);
}
