package com.sep6.backend.repository;

import com.sep6.backend.jpa.GenresJpaRepository;
import com.sep6.backend.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GenresRepositoryImpl implements GenresRepository {
    private GenresJpaRepository jpaRepository;
}
