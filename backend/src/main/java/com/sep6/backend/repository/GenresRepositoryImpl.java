package com.sep6.backend.repository;

import com.sep6.backend.jpa.GenresJpaRepository;
import com.sep6.backend.models.Genre;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class GenresRepositoryImpl implements GenresRepository {
    private GenresJpaRepository jpaRepository;

    @Override
    public List<Genre> getAllGenres() {
        log.info("Getting all genres");
        return jpaRepository.findAll();
    }

    @Override
    public Genre save(Genre genre) {
        log.info("Saving genre: {}", genre);
        return jpaRepository.save(genre);
    }

    @Override
    public Optional<Genre> findById(int id) {
        log.info("Finding genre by ID: {}", id);
        return jpaRepository.findById(id);
    }
}
