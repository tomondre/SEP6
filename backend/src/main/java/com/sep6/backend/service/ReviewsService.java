package com.sep6.backend.service;

import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;

import java.util.List;

public interface ReviewsService {
    List<Review> getMovieReviews(int id);
    Review createMovieReview(Review review);
    void recalculateRatingByMovie(Movie movie);
    Review updateMovieReview(Review review);
}
