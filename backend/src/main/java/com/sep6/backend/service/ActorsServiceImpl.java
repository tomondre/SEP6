package com.sep6.backend.service;

import com.sep6.backend.models.Person;
import com.sep6.backend.repository.ActorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorsServiceImpl implements ActorsService {
    private ActorsRepository repository;

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
