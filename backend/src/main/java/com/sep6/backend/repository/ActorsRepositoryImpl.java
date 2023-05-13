package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.jpa.PeopleJpaRepository;
import com.sep6.backend.models.Person;
import com.sep6.backend.models.PersonType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ActorsRepositoryImpl implements ActorsRepository
{
    private PeopleJpaRepository jpaRepository;

    @Override
    public List<Person> getActors()
    {
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