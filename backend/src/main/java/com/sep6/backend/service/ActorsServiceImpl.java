package com.sep6.backend.service;

import com.sep6.backend.repository.ActorsRepository;
import com.sep6.backend.repository.ActorsRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorsServiceImpl implements ActorsService {
    private ActorsRepository repository;

    @Autowired
    public ActorsServiceImpl(ActorsRepository repository) {
        this.repository = repository;
    }
}
