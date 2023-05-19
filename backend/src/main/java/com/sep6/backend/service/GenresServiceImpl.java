package com.sep6.backend.service;

import com.sep6.backend.models.Genre;
import com.sep6.backend.repository.GenresRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenresServiceImpl implements GenresService {
    private GenresRepository repository;

    @Override
    public List<Genre> getAllGenres() {
        return repository.getAllGenres();
    }

    @Override
    public Genre save(Genre genre)
    {
        return repository.save(genre);
    }
}
