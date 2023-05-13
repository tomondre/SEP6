package com.sep6.backend.service;

import com.sep6.backend.models.Genre;
import com.sep6.backend.repository.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenresServiceImpl implements GenresService {
    private GenresRepository repository;

    @Autowired
    public GenresServiceImpl(GenresRepository repository) {
        this.repository = repository;
    }
}
