package com.sep6.backend.controller;

import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.projections.MovieProjection;
import com.sep6.backend.service.MoviesService;
import com.sep6.backend.service.ReviewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MoviesControllerTest
{
    private MoviesController moviesController;

    @Mock
    private MoviesService moviesService;

    @Mock
    private ReviewsService reviewsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        moviesController = new MoviesController(moviesService, reviewsService);
    }

    @Test
    void testCreateMovie_ValidMovie_ReturnsCreatedMovie() {
        // Arrange
        Movie movie = new Movie();
        when(moviesService.createMovie(movie)).thenReturn(movie);

        // Act
        ResponseEntity<Movie> response = moviesController.createMovie(movie);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movie, response.getBody());
    }

    @Test
    void testCreateMovie_DuplicateMovie_ThrowsBadRequestException() {
        // Arrange
        Movie movie = new Movie();
        when(moviesService.createMovie(movie)).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> moviesController.createMovie(movie));
    }

    @Test
    void testGetMovies_NoParams_ReturnsAllMovies() {
        // Arrange
        List<MovieProjection> movies = new ArrayList<>();
        when(moviesService.getMovies()).thenReturn(movies);

        // Act
        ResponseEntity<List<MovieProjection>> response = moviesController.getMovies(Optional.empty(), Optional.empty(), Optional.empty());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
    }

    @Test
    void testGetMovies_SearchParam_ReturnsMoviesBySearch() {
        // Arrange
        String searchQuery = "action";
        List<MovieProjection> movies = new ArrayList<>();
        when(moviesService.getMoviesBySearch(searchQuery)).thenReturn(movies);

        // Act
        ResponseEntity<List<MovieProjection>> response = moviesController.getMovies(Optional.of(searchQuery), Optional.empty(), Optional.empty());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
    }

    @Test
    void testGetMovies_GenreIdParam_ReturnsMoviesByGenreId() {
        // Arrange
        String genreId = "1";
        int genreIdInt = Integer.parseInt(genreId);
        List<MovieProjection> movies = new ArrayList<>();
        when(moviesService.getMoviesByGenreId(genreIdInt)).thenReturn(movies);

        // Act
        ResponseEntity<List<MovieProjection>> response = moviesController.getMovies(Optional.empty(), Optional.of(genreId), Optional.empty());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
    }

    @Test
    void testGetMovies_PageParam_ReturnsPaginatedMovies() {
        // Arrange
        String page = "2";
        int pageInt = Integer.parseInt(page);
        List<MovieProjection> movies = new ArrayList<>();
        when(moviesService.getPaginatedMovies(pageInt)).thenReturn(movies);

        // Act
        ResponseEntity<List<MovieProjection>> response = moviesController.getMovies(Optional.empty(), Optional.empty(), Optional.of(page));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
    }

    @Test
    void testGetMovieById_ExistingMovieId_ReturnsMovie() {
        // Arrange
        int movieId = 1;
        Movie movie = new Movie();
        when(moviesService.getMovieById(movieId)).thenReturn(movie);

        // Act
        ResponseEntity<Movie> response = moviesController.getMovieById(movieId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movie, response.getBody());
    }

    @Test
    void testGetMovieById_NonExistingMovieId_ThrowsBadRequestException() {
        // Arrange
        int movieId = 1;
        when(moviesService.getMovieById(movieId)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> moviesController.getMovieById(movieId));
    }

    @Test
    void testUpdateMovieReview_ExistingMovieIdAndReviewId_ReturnsUpdatedReview() {
        // Arrange
        int movieId = 1;
        int reviewId = 1;
        Review review = new Review();
        when(reviewsService.updateMovieReview(review)).thenReturn(review);

        // Act
        ResponseEntity<Review> response = moviesController.updateMovieReview(movieId, reviewId, review);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(review, response.getBody());
    }

    @Test
    void testUpdateMovieReview_NonExistingMovieId_ThrowsBadRequestException() {
        // Arrange
        int movieId = 1;
        int reviewId = 1;
        Review review = new Review();
        when(reviewsService.updateMovieReview(review)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> moviesController.updateMovieReview(movieId, reviewId, review));
    }

    @Test
    void testDeleteMovieReview_ExistingMovieIdAndReviewId_ReturnsDeletedReview() {
        // Arrange
        int movieId = 1;
        int reviewId = 1;
        Review review = new Review();
        when(reviewsService.deleteMovieReview(reviewId, movieId)).thenReturn(review);

        // Act
        ResponseEntity<Review> response = moviesController.deleteMovieReview(movieId, reviewId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(review, response.getBody());
    }

    @Test
    void testDeleteMovieReview_NonExistingMovieId_ThrowsBadRequestException() {
        // Arrange
        int movieId = 1;
        int reviewId = 1;
        when(reviewsService.deleteMovieReview(reviewId, movieId)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> moviesController.deleteMovieReview(movieId, reviewId));
    }
}