package com.sep6.backend.repository;

import com.sep6.backend.models.Genre;

import java.util.List;

public interface GenresRepository {
    List<Genre> getAllGenres();
}
