package com.sep6.backend.service;

import com.sep6.backend.models.Genre;
import com.sep6.backend.repository.GenresRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GenresServiceImpl implements GenresService {
    private GenresRepository repository;

    @Override
    public List<Genre> getAllGenres() {
        log.info("Getting all genres");
        return repository.getAllGenres();
    }

    @Override
    public Genre save(Genre genre)
    {
        log.info("Saving genre: {}", genre);
        return repository.save(genre);
    }
}
