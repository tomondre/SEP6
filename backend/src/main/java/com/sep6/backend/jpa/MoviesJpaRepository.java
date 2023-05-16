package com.sep6.backend.jpa;

import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesJpaRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByTitleContainingIgnoreCase(String search);
    List<Movie> findByGenresId(int genreId);
    List<Movie> findAllByOrderByReleaseDateDesc(Pageable pageable);
    Movie findById(int id);
}
