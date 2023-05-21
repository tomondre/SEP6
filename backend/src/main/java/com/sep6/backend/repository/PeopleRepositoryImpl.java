package com.sep6.backend.repository;

import com.sep6.backend.jpa.PeopleJpaRepository;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
@Slf4j
public class PeopleRepositoryImpl implements PeopleRepository{
    private PeopleJpaRepository repository;

    @Override
    public List<PersonProjection> getPeopleBySearch(String search) {
        log.info("Fetching people by search: {}", search);
        return repository.findAllByNameContainingIgnoreCase(search);
    }

    @Override
    public List<PersonProjection> getPeople() {
        log.info("Fetching all people");
        return repository.findAllByIdNotNull();
    }

    @Override
    public Optional<PersonMoviesProjection> getPersonById(int id) {
        log.info("Fetching person by id: {}", id);
        return Optional.of(repository.findFirstById(id));
    }

    @Override
    public List<PersonProjection> getPaginatedPeople(int page) {
        log.info("Fetching paginated people: page {}", page);
        Pageable pageable = PageRequest.of(page, 12);
        return repository.findAll(pageable).getContent();
    }
}
