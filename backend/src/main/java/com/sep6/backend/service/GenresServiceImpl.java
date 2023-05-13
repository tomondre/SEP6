package com.sep6.backend.service;

import com.sep6.backend.models.Genre;
import com.sep6.backend.repository.GenresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenresServiceImpl implements GenresService {
    private GenresRepository repository;
}
