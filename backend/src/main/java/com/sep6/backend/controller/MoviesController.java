package com.sep6.backend.controller;

import com.sep6.backend.models.Genre;
import com.sep6.backend.models.Movie;
import com.sep6.backend.service.MoviesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MoviesController {
    private MoviesService service;

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return service.createMovie(movie);
    }

    @GetMapping
    public List<Movie> getMovies(
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
    public List<Movie> getLatestMovies(@RequestParam(name = "limit", required = false) String limit) {
        int actualLimit = limit != null && !limit.isEmpty()? Integer.parseInt(limit): 10;
        return service.getLatestMovies(actualLimit);
    }
}
