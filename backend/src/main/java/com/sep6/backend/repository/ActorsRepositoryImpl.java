package com.sep6.backend.repository;

import com.sep6.backend.jpa.PeopleJpaRepository;
import com.sep6.backend.models.Person;
import com.sep6.backend.models.PersonType;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class ActorsRepositoryImpl implements ActorsRepository
{
    private PeopleJpaRepository jpaRepository;

    @Override
    public List<PersonProjection> getActors()
    {
        log.info("Getting all actors");
        return jpaRepository.getAllByType(PersonType.ACTOR);
    }

    @Override
    public List<PersonProjection> getActorsBySearch(String search)
    {
        log.info("Getting actors by search: {}", search);
        return jpaRepository.findAllByTypeAndNameContainingIgnoreCase(PersonType.ACTOR, search);
    }

    @Override
    public Optional<PersonMoviesProjection> getActorById(int id)
    {
        log.info("Getting actor by ID: {}", id);
        return jpaRepository.findByTypeAndId(PersonType.ACTOR, id);
    }

    @Override
    public Optional<PersonMoviesProjection> findById(int id)
    {
        log.info("Finding person by ID: {}", id);
        return Optional.of(jpaRepository.findFirstById(id));
    }

    @Override
    public Person save(Person person)
    {
        log.info("Saving person: {}", person);
        return jpaRepository.save(person);
    }
}