package com.sep6.backend.service;

import com.sep6.backend.models.Person;
import com.sep6.backend.repository.ActorsRepository;
import com.sep6.backend.repository.ActorsRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorsServiceImpl implements ActorsService {
    private ActorsRepository repository;

    @Autowired
    public ActorsServiceImpl(ActorsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> getActorsBySearch(String search) {
        return repository.getActorsBySearch(search);
    }

    @Override
    public List<Person> getActors() {
        return repository.getActors();
    }

    @Override
    public Person getActorById(int id) {
        return repository.getActorById(id);
    }
}
