package com.sep6.backend.controller;

import com.sep6.backend.models.Genre;
import com.sep6.backend.models.Movie;
import com.sep6.backend.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MoviesController {
    private MoviesService service;

    @Autowired
    public MoviesController(MoviesService service) {
        this.service = service;
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
}
