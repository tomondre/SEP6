package com.sep6.backend.repository;

import com.sep6.backend.models.Review;

import java.util.List;

public interface ReviewsRepository {
    List<Review> getMovieReviews(int id);
    Review createMovieReview(Review review);
    Review updateMovieReview(Review review);
}
