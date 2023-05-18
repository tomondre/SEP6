package com.sep6.backend.controller;


import com.sep6.backend.models.Person;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.service.DirectorsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
@AllArgsConstructor
public class DirectorsController {
    private DirectorsService service;

    @GetMapping
    public List<PersonProjection> getDirectors(@RequestParam(name = "search", required = false) String search) {
        if (search != null) {
            return service.getDirectorsBySearch(search);
        }
        return service.getDirectors();
    }

    @GetMapping(value = "/{id}")
    public PersonMoviesProjection getDirectorById(@PathVariable int id) {
        return service.getDirectorsById(id);
    }
}
