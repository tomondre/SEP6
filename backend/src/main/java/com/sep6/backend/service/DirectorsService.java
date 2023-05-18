package com.sep6.backend.service;


import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;

import java.util.List;
import java.util.Optional;

public interface DirectorsService {
    List<PersonProjection> getDirectorsBySearch(String search);
    List<PersonProjection> getDirectors();
    Optional<PersonMoviesProjection> getDirectorsById(int id);
}
