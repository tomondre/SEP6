package com.sep6.backend.repository;

import com.sep6.backend.jpa.GenresJpaRepository;
import com.sep6.backend.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenresRepositoryImpl implements GenresRepository {
    private GenresJpaRepository jpaRepository;


    @Override
    public List<Genre> getAllGenres() {
        return jpaRepository.findAll();
    }
}
