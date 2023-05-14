package com.sep6.backend.controller;


import com.sep6.backend.models.Person;
import com.sep6.backend.service.DirectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorsController {
    private DirectorsService service;

    @Autowired
    public DirectorsController(DirectorsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Person> getDirectors(@RequestParam(name = "search", required = false) String search) {
        if (search != null) {
            return service.getDirectorsBySearch(search);
        }
        return service.getDirectors();
    }

    @GetMapping(value = "/{id}")
    public Person getDirectorById(@PathVariable int id) {
        return service.getDirectorsById(id);
    }
}
