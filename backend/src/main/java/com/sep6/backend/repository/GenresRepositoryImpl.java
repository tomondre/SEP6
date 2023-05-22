package com.sep6.backend.repository;

import com.sep6.backend.jpa.GenresJpaRepository;
import com.sep6.backend.models.Genre;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GenresRepositoryImpl implements GenresRepository {
    private GenresJpaRepository jpaRepository;


    @Override
    public List<Genre> getAllGenres() {
        return jpaRepository.findAll();
    }

    @Override
    public Genre save(Genre genre)
    {
        return jpaRepository.save(genre);
    }

    @Override
    public Optional<Genre> findById(int id)
    {
        return jpaRepository.findById(id);
    }
}
