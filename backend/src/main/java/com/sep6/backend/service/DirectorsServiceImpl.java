package com.sep6.backend.service;


import com.sep6.backend.models.Person;
import com.sep6.backend.repository.DirectorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorsServiceImpl implements DirectorsService {
    private DirectorsRepository repository;

    @Override
    public List<Person> getDirectorsBySearch(String search) {
        return repository.getDirectorsBySearch(search);
    }

    @Override
    public List<Person> getDirectors() {
        return repository.getDirectors();
    }

    @Override
    public Person getDirectorsById(int id) {
        return repository.getDirectorsById(id);
    }
}
