package com.sep6.backend.service;

import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.repository.MoviesRepository;
import com.sep6.backend.repository.ReviewsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ReviewsServiceImplTest
{
    @Mock
    private ReviewsRepository repository;

    @Mock
    private MoviesRepository moviesRepository;

    private ReviewsService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new ReviewsServiceImpl(repository, moviesRepository);
    }

    @Test
    public void testGetMovieReviews() {
        // Arrange
        int movieId = 1;
        List<Review> reviews = new ArrayList<>();
        when(repository.getMovieReviews(movieId)).thenReturn(reviews);

        // Act
        List<Review> result = service.getMovieReviews(movieId);

        // Assert
        assertEquals(reviews, result);
        verify(repository, times(1)).getMovieReviews(movieId);
    }

    @Test
    public void testCreateMovieReview() {
        // Arrange
        Review review = mock(Review.class);
        Review createdReview = mock(Review.class);
        int movieId = 1;
        when(repository.createMovieReview(review)).thenReturn(createdReview);
        when(review.getMovieId()).thenReturn(movieId);
        when(moviesRepository.getMovieById(movieId)).thenReturn(Optional.of(mock(Movie.class)));

        // Act
        Review result = service.createMovieReview(review);

        // Assert
        assertEquals(createdReview, result);
        verify(repository, times(1)).createMovieReview(review);
        verify(moviesRepository, times(1)).getMovieById(movieId);
    }

    @Test
    public void testUpdateMovieReview() {
        // Arrange
        Review review = mock(Review.class);
        Review updatedReview = mock(Review.class);
        Movie movie = mock(Movie.class);
        int movieId = 1;
        when(repository.updateMovieReview(review)).thenReturn(updatedReview);
        when(moviesRepository.getMovieById(movieId)).thenReturn(Optional.of(movie));
        when(review.getMovieId()).thenReturn(movieId);

        // Act
        Review result = service.updateMovieReview(review);

        // Assert
        assertEquals(updatedReview, result);
        verify(repository, times(1)).updateMovieReview(review);
        verify(moviesRepository, times(1)).getMovieById(movieId);
    }

    @Test
    public void testDeleteMovieReview() {
        // Arrange
        int reviewId = 1;
        int movieId = 1;
        Review review = mock(Review.class);
        when(repository.deleteReview(reviewId)).thenReturn(review);
        when(moviesRepository.getMovieById(movieId)).thenReturn(Optional.of(mock(Movie.class)));

        // Act
        Review result = service.deleteMovieReview(reviewId, movieId);

        // Assert
        assertEquals(review, result);
        verify(repository, times(1)).deleteReview(reviewId);
        verify(moviesRepository, times(1)).getMovieById(movieId);
    }
}