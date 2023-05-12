package com.sep6.backend.repository;

import com.sep6.backend.jpa.MoviesJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MoviesRepositoryImpl implements MoviesRepository{
    private MoviesJpaRepository jpaRepository;

    public MoviesRepositoryImpl(MoviesJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
}
