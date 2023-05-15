package com.sep6.backend.repository;

import com.sep6.backend.jpa.MoviesJpaRepository;
import com.sep6.backend.jpa.ReviewsJpaRepository;
import com.sep6.backend.models.Account;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ReviewsRepositoryImpl implements ReviewsRepository {
    private ReviewsJpaRepository jpaRepository;
    private MoviesRepository moviesRepository;
    private AccountsRepository accountsRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Review> getMovieReviews(int id) {
        return jpaRepository.findReviewsByMovieId(id);
    }

    @Override
    public Review createMovieReview(Review review) {
        if (review.getCreatedOn() == null)
            review.setCreatedOn(LocalDateTime.now());
        Movie movieById = moviesRepository.getMovieById(review.getMovieId());
        Account account = accountsRepository.getAccountById(review.getAccountId());

        review.setMovie(movieById);
        review.setAccount(account);

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
