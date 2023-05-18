package com.sep6.backend.repository;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;

import java.util.List;
import java.util.Optional;

public interface DirectorsRepository {
    List<PersonProjection> getDirectors();
    List<PersonProjection> getDirectorsBySearch(String search);
    Optional<PersonMoviesProjection> getDirectorsById(int id);
}
