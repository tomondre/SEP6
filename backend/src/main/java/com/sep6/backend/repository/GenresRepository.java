package com.sep6.backend.repository;

import com.sep6.backend.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenresRepository {
    List<Genre> getAllGenres();
    Genre save(Genre genre);
    Optional<Genre> findById(int id);
}
