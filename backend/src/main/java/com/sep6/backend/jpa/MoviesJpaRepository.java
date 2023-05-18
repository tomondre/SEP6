package com.sep6.backend.jpa;

import com.sep6.backend.projections.MovieBasicInfoProjection;
import com.sep6.backend.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesJpaRepository extends JpaRepository<Movie, Integer> {
    List<MovieBasicInfoProjection> findByTitleContainingIgnoreCase(String search);
    List<MovieBasicInfoProjection> findByGenresId(int genreId);
    List<MovieBasicInfoProjection> findAllByOrderByReleaseDateDesc(Pageable pageable);
    Movie findById(int id);
    // Workaround because the return projection would otherwise clash with the already implemented findAll method
    Page<MovieBasicInfoProjection> findAllByIdNotNull(Pageable page);
    List<MovieBasicInfoProjection> findAllByIdNotNull();
}
