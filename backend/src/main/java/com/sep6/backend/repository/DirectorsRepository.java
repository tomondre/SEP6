package com.sep6.backend.repository;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;

import java.util.List;

public interface DirectorsRepository {
    List<PersonProjection> getDirectors();
    List<PersonProjection> getDirectorsBySearch(String search);
    PersonMoviesProjection getDirectorsById(int id);
}
