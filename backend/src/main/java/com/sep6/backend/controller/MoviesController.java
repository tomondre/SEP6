package com.sep6.backend.controller;

import com.sep6.backend.models.Genre;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.service.MoviesService;
import com.sep6.backend.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MoviesController {
    private MoviesService service;
    private ReviewsService reviewsService;

    @Autowired
    public MoviesController(MoviesService service, ReviewsService reviewsService) {
        this.service = service;
        this.reviewsService = reviewsService;
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return service.createMovie(movie);
    }

    @GetMapping
    public List<Movie> getMovies(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "genreId", required = false) String genreId
    ) {
        if (search != null) {
            return service.getMoviesBySearch(search);
        } else if (genreId != null && !genreId.isEmpty()) {
            var genreIdInt = Integer.parseInt(genreId);
            return service.getMoviesByGenreId(genreIdInt);
        }
            return service.getMovies();
    }

    @GetMapping(value = "/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return service.getMovieById(id);
    }

    @GetMapping(value = "/latest")
    public List<Movie> getLatestMovies(@RequestParam(name = "limit", required = false) String limit) {
        int actualLimit = limit != null && !limit.isEmpty()? Integer.parseInt(limit): 10;
        return service.getLatestMovies(actualLimit);
    }

    @PostMapping(value = "/{id}/reviews")
    public Review createMovieReview(@PathVariable int id, @RequestBody Review review) {
        review.setMovieId(id);
        return reviewsService.createMovieReview(review);
    }

    @GetMapping(value = "/{id}/reviews")
    public List<Review> getMovieReviews(@PathVariable int id) {
        return reviewsService.getMovieReviews(id);
    }

    @PutMapping(value = "/{movieId}/reviews/{reviewId}")
    public Review updateMovieReview(@PathVariable int movieId, @PathVariable int reviewId, @RequestBody Review review) {
        review.setMovieId(movieId);
        review.setId(reviewId);
        return reviewsService.updateMovieReview(review);
    }

    @DeleteMapping(value = "/{movieId}/reviews/{reviewId}")
    public Review deleteMovieReview(@PathVariable int movieId, @PathVariable int reviewId) {
        return reviewsService.deleteMovieReview(reviewId, movieId);
    }
}
