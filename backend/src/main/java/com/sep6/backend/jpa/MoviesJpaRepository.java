package com.sep6.backend.jpa;

import com.sep6.backend.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoviesJpaRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByTitleContainingIgnoreCase(String search);
    List<Movie> findByGenresId(int genreId);
    List<Movie> findAllByOrderByReleaseDateDesc(Pageable pageable);
    Page<Movie> findAll(Pageable page);
    Optional<Movie> findById(int id);
}
