package com.sep6.backend.repository;

import com.sep6.backend.jpa.MoviesJpaRepository;
import com.sep6.backend.jpa.ReviewsJpaRepository;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReviewsRepositoryImpl implements ReviewsRepository {
    private ReviewsJpaRepository jpaRepository;
    private MoviesRepository moviesRepository;
    @PersistenceContext
    private EntityManager entityManager;


    public ReviewsRepositoryImpl(ReviewsJpaRepository jpaRepository, MoviesRepository moviesRepository) {
        this.jpaRepository = jpaRepository;
        this.moviesRepository = moviesRepository;
    }

    @Override
    public List<Review> getMovieReviews(int id) {
        return jpaRepository.findReviewsByMovieId(id);
    }

    @Override
    public Review createMovieReview(Review review) {
        if (review.getCreatedOn() == null)
            review.setCreatedOn(LocalDateTime.now());
        Movie movieById = moviesRepository.getMovieById(review.getMovieId());
        review.setMovie(movieById);
        return jpaRepository.save(review);
    }

    @Override
    public Review updateMovieReview(Review review) {
        Review reviewById = getReviewById(review.getId());
        reviewById.setComment(review.getComment());
        reviewById.setRating(review.getRating());
        reviewById.setCreatedOn(LocalDateTime.now());
        return jpaRepository.save(reviewById);
    }

    @Override
    public Review deleteReview(int reviewId) {
        return jpaRepository.deleteById(reviewId);
    }

    public Review getReviewById(int id) {
        return jpaRepository.findById(id);
    }
}
