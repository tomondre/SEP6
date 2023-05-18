package com.sep6.backend.controller;
import com.sep6.backend.models.Person;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.service.ActorsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
@AllArgsConstructor
public class ActorsController {
    private ActorsService service;

    @GetMapping
    public List<PersonProjection> getActors(@RequestParam(name = "search", required = false) String search) {
        if (search != null) {
            return service.getActorsBySearch(search);
        }
        return service.getActors();
    }

    @GetMapping(value = "/{id}")
    public PersonMoviesProjection getActorById(@PathVariable int id) {
        return service.getActorById(id);
    }
}
