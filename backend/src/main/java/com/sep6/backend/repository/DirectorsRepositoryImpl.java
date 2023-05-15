package com.sep6.backend.repository;

import com.sep6.backend.jpa.PeopleJpaRepository;
import com.sep6.backend.models.Person;
import com.sep6.backend.models.PersonType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DirectorsRepositoryImpl implements DirectorsRepository{
    private PeopleJpaRepository jpaRepository;

    @Override
    public List<Person> getDirectors() {
        return jpaRepository.getAllByType(PersonType.DIRECTOR);
    }

    @Override
    public List<Person> getDirectorsBySearch(String search) {
        return jpaRepository.findAllByTypeAndNameContainingIgnoreCase(PersonType.DIRECTOR, search);
    }

    @Override
    public Person getDirectorsById(int id) {
        return jpaRepository.findByTypeAndId(PersonType.DIRECTOR, id);
    }
}
