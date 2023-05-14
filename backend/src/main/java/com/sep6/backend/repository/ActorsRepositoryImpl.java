package com.sep6.backend.repository;

import com.sep6.backend.jpa.PeopleJpaRepository;
import com.sep6.backend.models.Person;
import com.sep6.backend.models.PersonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ActorsRepositoryImpl implements ActorsRepository{
    private PeopleJpaRepository jpaRepository;

    @Autowired
    public ActorsRepositoryImpl(PeopleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Person> getActors() {
        return jpaRepository.getAllByType(PersonType.ACTOR);
    }

    @Override
    public List<Person> getActorsBySearch(String search) {
        return jpaRepository.findAllByTypeAndNameContainingIgnoreCase(PersonType.ACTOR, search);
    }

    @Override
    public Person getActorById(int id) {
        return jpaRepository.findByTypeAndId(PersonType.ACTOR, id);
    }
}