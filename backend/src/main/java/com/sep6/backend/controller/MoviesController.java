package com.sep6.backend.controller;

import com.sep6.backend.projections.MovieBasicInfoProjection;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.service.MoviesService;
import com.sep6.backend.service.ReviewsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesController {
    private MoviesService service;
    private ReviewsService reviewsService;

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return service.createMovie(movie);
    }

    @GetMapping
    public List<MovieBasicInfoProjection> getMovies(
            @RequestParam(name = "search", required = false) Optional<String> search,
            @RequestParam(name = "genreId", required = false) Optional<String> genreId,
            @RequestParam(name = "page", required = false) Optional<String> page
    ) {
        if (search.isPresent()) {
            return service.getMoviesBySearch(search.get());
        }

        if (genreId.isPresent() && !genreId.get().isEmpty()) {
            int genreIdInt = Integer.parseInt(genreId.get());
            return service.getMoviesByGenreId(genreIdInt);
        }

        if (page.isPresent() && !page.get().isEmpty()) {
            int pageInt = Integer.parseInt(page.get());
            return service.getPaginatedMovies(pageInt);
        }

        return service.getMovies();
    }

    @GetMapping(value = "/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return service.getMovieById(id);
    }

    @GetMapping(value = "/latest")
    public List<MovieBasicInfoProjection> getLatestMovies(@RequestParam(name = "limit", required = false) String limit) {
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
