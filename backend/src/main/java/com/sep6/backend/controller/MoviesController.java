package com.sep6.backend.controller;

import com.sep6.backend.projections.MovieProjection;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.service.MoviesService;
import com.sep6.backend.service.ReviewsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/movies")
@AllArgsConstructor
@Slf4j
public class MoviesController {
    private MoviesService service;
    private ReviewsService reviewsService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        try {
            log.info("Creating movie: {}", movie);
            return ResponseEntity.ok(service.createMovie(movie));
        } catch (IllegalArgumentException e) {
            log.error("Error creating movie with id {}", movie.getId(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with id " + movie.getId() + " already exists.");
        } catch (Exception e) {
            log.error("An error occurred while creating the movie", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @GetMapping
    public ResponseEntity<List<MovieProjection>> getMovies(
            @RequestParam(name = "search", required = false) Optional<String> search,
            @RequestParam(name = "genreId", required = false) Optional<String> genreId,
            @RequestParam(name = "page", required = false) Optional<String> page
    ) {
        try {
            log.info("Fetching movies with search={}, genreId={}, page={}", search, genreId, page);

            if (search.isPresent()) {
                return ResponseEntity.ok(service.getMoviesBySearch(search.get()));
            }

            if (genreId.isPresent() && !genreId.get().isEmpty()) {
                int genreIdInt = Integer.parseInt(genreId.get());
                return ResponseEntity.ok(service.getMoviesByGenreId(genreIdInt));
            }

            if (page.isPresent() && !page.get().isEmpty()) {
                int pageInt = Integer.parseInt(page.get());
                return ResponseEntity.ok(service.getPaginatedMovies(pageInt));
            }

            return ResponseEntity.ok(service.getMovies());
        } catch (Exception e) {
            log.error("An error occurred while fetching movies", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        try {
            log.info("Fetching movie with id {}", id);
            return ResponseEntity.ok(service.getMovieById(id));
        } catch (NoSuchElementException e) {
            log.error("Movie with id {} does not exist", id, e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with id " + id + " does not exist.");
        } catch (Exception e) {
            log.error("An error occurred while fetching the movie", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @GetMapping(value = "/latest")
    public ResponseEntity<List<MovieProjection>> getLatestMovies(@RequestParam(name = "limit", required = false) String limit) {
        try {
            int actualLimit = limit != null && !limit.isEmpty() ? Integer.parseInt(limit) : 10;
            return ResponseEntity.ok(service.getLatestMovies(actualLimit));
        } catch (Exception e) {
            log.error("An error occurred while retrieving latest movies: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @PostMapping(value = "/{id}/reviews")
    public ResponseEntity<Review> createMovieReview(@PathVariable int id, @RequestBody Review review) {
        try {
            review.setMovieId(id);
            return ResponseEntity.ok(reviewsService.createMovieReview(review));
        } catch (NoSuchElementException e) {
            log.error("Movie with id {} does not exist.", id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with id " + id + " does not exist.");
        } catch (Exception e) {
            log.error("An error occurred while creating movie review: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<Review>> getMovieReviews(@PathVariable int id) {
        try {
            return ResponseEntity.ok(reviewsService.getMovieReviews(id));
        } catch (Exception e) {
            log.error("An error occurred while retrieving movie reviews: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @PutMapping(value = "/{movieId}/reviews/{reviewId}")
    public ResponseEntity<Review> updateMovieReview(@PathVariable int movieId, @PathVariable int reviewId, @RequestBody Review review) {
        try {
            review.setMovieId(movieId);
            review.setId(reviewId);
            return ResponseEntity.ok(reviewsService.updateMovieReview(review));
        } catch (NoSuchElementException e) {
            log.error("Movie with id {} does not exist.", movieId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with id " + movieId + " does not exist.");
        } catch (Exception e) {
            log.error("An error occurred while updating movie review: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @DeleteMapping(value = "/{movieId}/reviews/{reviewId}")
    public ResponseEntity<Review> deleteMovieReview(@PathVariable int movieId, @PathVariable int reviewId) {
        try {
            return ResponseEntity.ok(reviewsService.deleteMovieReview(reviewId, movieId));
        } catch (NoSuchElementException e) {
            log.error("Movie with id {} does not exist.", movieId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with id " + movieId + " does not exist.");
        } catch (Exception e) {
            log.error("An error occurred while deleting movie review: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }
}
