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
public class DirectorsRepositoryImpl implements DirectorsRepository{
    private PeopleJpaRepository jpaRepository;

    @Override
    public List<PersonProjection> getDirectors() {
        return jpaRepository.getAllByType(PersonType.DIRECTOR);
    }

    @Override
    public List<PersonProjection> getDirectorsBySearch(String search) {
        return jpaRepository.findAllByTypeAndNameContainingIgnoreCase(PersonType.DIRECTOR, search);
    }

    @Override
    public PersonMoviesProjection getDirectorsById(int id) {
        return jpaRepository.findByTypeAndId(PersonType.DIRECTOR, id);
    }
}
