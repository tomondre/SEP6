package com.sep6.backend.service;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {
    private PeopleRepository repository;

    @Override
    public List<PersonProjection> getPeopleBySearch(String search) {
        return repository.getPeopleBySearch(search);
    }

    @Override
    public List<PersonProjection> getPeople() {
        return repository.getPeople();
    }

    @Override
    public PersonMoviesProjection getPersonById(int id) {
        return repository.getPersonById(id).orElseThrow();
    }

    @Override
    public List<PersonProjection> getPaginatedPeople(int page) {
        return repository.getPaginatedPeople(page);
    }
}
