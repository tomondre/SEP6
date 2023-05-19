package com.sep6.backend.service;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;

import java.util.List;

public interface ActorsService {
    List<PersonProjection> getActorsBySearch(String search);
    List<PersonProjection> getActors();
    PersonMoviesProjection getActorById(int id);
}
