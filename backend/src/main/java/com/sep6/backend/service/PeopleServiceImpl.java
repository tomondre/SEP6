package com.sep6.backend.service;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PeopleServiceImpl implements PeopleService {
    private PeopleRepository repository;

    @Override
    public List<PersonProjection> getPeopleBySearch(String search) {
        log.info("Fetching people by search: {}", search);
        return repository.getPeopleBySearch(search);
    }

    @Override
    public List<PersonProjection> getPeople() {
        log.info("Fetching all people");
        return repository.getPeople();
    }

    @Override
    public PersonMoviesProjection getPersonById(int id) {
        log.info("Fetching person by id: {}", id);
        return repository.getPersonById(id).orElseThrow();
    }

    @Override
    public List<PersonProjection> getPaginatedPeople(int page) {
        log.info("Fetching paginated people: page {}", page);
        return repository.getPaginatedPeople(page);
    }
}
