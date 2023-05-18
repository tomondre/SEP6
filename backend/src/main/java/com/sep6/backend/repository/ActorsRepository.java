package com.sep6.backend.repository;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;

import java.util.List;

public interface ActorsRepository {
    List<PersonProjection> getActors();
    List<PersonProjection> getActorsBySearch(String search);
    PersonMoviesProjection getActorById(int id);
}
