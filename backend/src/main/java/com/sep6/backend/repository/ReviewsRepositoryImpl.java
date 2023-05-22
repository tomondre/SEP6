package com.sep6.backend.repository;

import com.sep6.backend.jpa.ReviewsJpaRepository;
import com.sep6.backend.models.Account;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class ReviewsRepositoryImpl implements ReviewsRepository {
    private ReviewsJpaRepository jpaRepository;
    private MoviesRepository moviesRepository;
    private AccountsRepository accountsRepository;

    @Override
    public List<Review> getMovieReviews(int id) {
        log.info("Getting reviews for movie with ID: {}", id);
        return jpaRepository.findReviewsByMovieId(id);
    }

    @Override
    public Review createMovieReview(Review review) {
        log.info("Creating movie review: {}", review);

        if (review.getCreatedOn() == null)
            review.setCreatedOn(LocalDateTime.now());
        Movie movieById = moviesRepository.getMovieById(review.getMovieId()).orElseThrow();
        Account account = accountsRepository.getAccountById(review.getAccountId()).orElseThrow();

        review.setMovie(movieById);
        review.setAccount(account);

        return jpaRepository.save(review);
    }

    @Override
    public Review updateMovieReview(Review review) {
        log.info("Updating movie review: {}", review);

        Review reviewById = getReviewById(review.getId()).orElseThrow();
        reviewById.setComment(review.getComment());
        reviewById.setRating(review.getRating());
        reviewById.setCreatedOn(LocalDateTime.now());
        return jpaRepository.save(reviewById);
    }

    @Override
    public Review deleteReview(int reviewId) {
        log.info("Deleting review with ID: {}", reviewId);
        return jpaRepository.deleteById(reviewId);
    }

    public Optional<Review> getReviewById(int id) {
        log.info("Getting review by ID: {}", id);
        return jpaRepository.findById(id);
    }
}
