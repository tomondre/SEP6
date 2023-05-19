package com.sep6.backend.repository;

import com.sep6.backend.jpa.PeopleJpaRepository;
import com.sep6.backend.models.Person;
import com.sep6.backend.models.PersonType;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<PersonMoviesProjection> getActorById(int id) {
        return jpaRepository.findByTypeAndId(PersonType.ACTOR, id);
    }

    @Override
    public Optional<Person> findById(int id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Person save(Person person)
    {
        return jpaRepository.save(person);
    }
}