package com.sep6.backend.controller;
import com.sep6.backend.models.Person;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.service.ActorsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/actors")
@AllArgsConstructor
@Slf4j
public class ActorsController {
    private ActorsService service;

    @GetMapping
    public ResponseEntity<List<PersonProjection>> getActors(@RequestParam(name = "search", required = false) String search) {
        try {
            log.info("Fetching actors");
            if (search != null) {
                log.info("Searching actors by search query: {}", search);
                return ResponseEntity.ok(service.getActorsBySearch(search));
            }
            return ResponseEntity.ok(service.getActors());
        } catch (Exception e) {
            log.error("An error occurred while fetching actors", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, please try again later");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonMoviesProjection> getActorById(@PathVariable int id) {
        try {
            log.info("Fetching actor by ID: {}", id);
            return ResponseEntity.ok(service.getActorById(id));
        } catch (NoSuchElementException e) {
            log.warn("Actor with ID {} does not exist.", id, e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Actor with id " + id + " does not exist.", e);
        } catch (Exception e) {
            log.error("An error occurred while fetching actor with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, please try again later");
        }
    }
}
