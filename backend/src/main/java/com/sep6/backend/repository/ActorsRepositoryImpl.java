package com.sep6.backend.repository;

import com.sep6.backend.jpa.PeopleJpaRepository;
import com.sep6.backend.models.PersonType;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ActorsRepositoryImpl implements ActorsRepository
{
    private PeopleJpaRepository jpaRepository;

    @Override
    public List<PersonProjection> getActors()
    {
        return jpaRepository.getAllByType(PersonType.ACTOR);
    }

    @Override
    public List<PersonProjection> getActorsBySearch(String search) {
        return jpaRepository.findAllByTypeAndNameContainingIgnoreCase(PersonType.ACTOR, search);
    }

    @Override
    public PersonMoviesProjection getActorById(int id) {
        return jpaRepository.findByTypeAndId(PersonType.ACTOR, id);
    }
}