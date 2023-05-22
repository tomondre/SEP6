package com.sep6.backend.service;

import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.repository.MoviesRepository;
import com.sep6.backend.repository.ReviewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewsServiceImpl implements ReviewsService {
    private ReviewsRepository repository;
    private MoviesRepository moviesRepository;
    @Override
    public List<Review> getMovieReviews(int id) {
        return repository.getMovieReviews(id);
    }

    @Override
    public Review createMovieReview(Review review)
    {
        Review createdReview = repository.createMovieReview(review);
        recalculateRatingByMovieId(review.getMovieId());
        return createdReview;
    }

    public void recalculateRatingByMovieId(int movieId)
    {
        Movie movieById = moviesRepository.getMovieById(movieId).orElseThrow();
        recalculateRatingByMovie(movieById);
    }

    public void recalculateRatingByMovie(Movie movie) {
        double noOfReviews = movie.getReviews().size();
        if (noOfReviews == 0) {
            return;
        }
        double ratingSum = 0;
        for (Review movieReview : movie.getReviews()) {
            ratingSum += movieReview.getRating();
        }
        double newRating = ratingSum / noOfReviews;
        movie.setRating(newRating);
        moviesRepository.updateMovieRatingById(movie.getId(), newRating);
    }

    @Override
    public Review updateMovieReview(Review review) {
        Review updatedReview = repository.updateMovieReview(review);
        recalculateRatingByMovieId(review.getMovieId());
        return updatedReview;
    }

    @Override
    public Review deleteMovieReview(int reviewId, int movieId) {
        Review review = repository.deleteReview(reviewId);
        recalculateRatingByMovieId(movieId);
        return review;
    }
}
