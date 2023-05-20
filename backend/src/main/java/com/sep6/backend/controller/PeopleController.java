package com.sep6.backend.controller;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/people")
@AllArgsConstructor
public class PeopleController {
    private PeopleService service;

    @GetMapping
    public ResponseEntity<List<PersonProjection>> getPeople(
            @RequestParam(name = "search", required = false) Optional<String> search,
            @RequestParam(name = "page", required = false) Optional<Integer> page
    ) {
        try
        {
            if (page.isPresent()) {
                return ResponseEntity.ok(service.getPaginatedPeople(page.get()));
            }
            if (search.isPresent() && !search.get().isEmpty()) {
                return ResponseEntity.ok(service.getPeopleBySearch(search.get()));
            }

            return ResponseEntity.ok(service.getPeople());
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, please try again later");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonMoviesProjection> getPersonById(@PathVariable int id) {
        try
        {
            return ResponseEntity.ok(service.getPersonById(id));
        }
        catch (NullPointerException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person with id " + id + " does not exist.", e);
        }
        catch (Exception e)
        {
            //TODO logg the error
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, please try again later");
        }
    }
}
