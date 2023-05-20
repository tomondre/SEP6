package com.sep6.backend.repository;

import com.sep6.backend.jpa.PeopleJpaRepository;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class PeopleRepositoryImpl implements PeopleRepository{
    private PeopleJpaRepository repository;

    @Override
    public List<PersonProjection> getPeopleBySearch(String search) {
        return repository.findAllByNameContainingIgnoreCase(search);
    }

    @Override
    public List<PersonProjection> getPeople() {
        return repository.findAllByIdNotNull();
    }

    @Override
    public Optional<PersonMoviesProjection> getPersonById(int id) {
        return Optional.of(repository.findFirstById(id));
    }

    @Override
    public List<PersonProjection> getPaginatedPeople(int page) {
        Pageable pageable = PageRequest.of(page, 12);
        return repository.findAll(pageable).getContent();
    }
}
