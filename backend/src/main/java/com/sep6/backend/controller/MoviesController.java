package com.sep6.backend.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.sep6.backend.models.Movie;
import com.sep6.backend.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
