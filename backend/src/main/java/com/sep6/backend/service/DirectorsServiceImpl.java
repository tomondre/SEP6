package com.sep6.backend.service;


import com.sep6.backend.models.Person;
import com.sep6.backend.repository.ActorsRepository;
import com.sep6.backend.repository.DirectorsRepository;
import com.sep6.backend.repository.DirectorsRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorsServiceImpl implements DirectorsService {
    private DirectorsRepository repository;

    @Autowired
    public DirectorsServiceImpl(DirectorsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> getDirectorsBySearch(String search) {
        return repository.getDirectorsBySearch(search);
    }

    @Override
    public List<Person> getDirectors() {
        return repository.getDirectors();
    }
}
