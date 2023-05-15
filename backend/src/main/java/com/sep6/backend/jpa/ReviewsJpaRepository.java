package com.sep6.backend.jpa;

import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ReviewsJpaRepository extends CrudRepository<Review, Integer> {
    List<Review> findReviewsByMovieId(int movie_id);
    Review findById(int id);
    Review deleteById(int id);
}
