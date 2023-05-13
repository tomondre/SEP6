package com.sep6.backend.jpa;

import com.sep6.backend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesJpaRepository extends JpaRepository<Movie, Integer> {
    public List<Movie> findByTitleContainingIgnoreCase(String search);
}
