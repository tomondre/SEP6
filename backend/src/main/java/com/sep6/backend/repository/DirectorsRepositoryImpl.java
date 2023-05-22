package com.sep6.backend.repository;

import com.sep6.backend.jpa.PeopleJpaRepository;
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
public class DirectorsRepositoryImpl implements DirectorsRepository{
    private PeopleJpaRepository jpaRepository;

    @Override
    public List<PersonProjection> getDirectors() {
        log.info("Getting all directors");
        return jpaRepository.getAllByType(PersonType.DIRECTOR);
    }

    @Override
    public List<PersonProjection> getDirectorsBySearch(String search) {
        log.info("Getting directors by search: {}", search);
        return jpaRepository.findAllByTypeAndNameContainingIgnoreCase(PersonType.DIRECTOR, search);
    }

    @Override
    public Optional<PersonMoviesProjection> getDirectorsById(int id) {
        log.info("Getting director by ID: {}", id);
        return jpaRepository.findByTypeAndId(PersonType.DIRECTOR, id);
    }
}
