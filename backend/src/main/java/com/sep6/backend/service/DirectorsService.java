package com.sep6.backend.service;


import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;

import java.util.List;

public interface DirectorsService {
    List<PersonProjection> getDirectorsBySearch(String search);
    List<PersonProjection> getDirectors();
    PersonMoviesProjection getDirectorById(int id);
}
