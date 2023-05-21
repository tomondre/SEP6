package com.sep6.backend.controller;


import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.service.DirectorsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/directors")
@AllArgsConstructor
@Slf4j
public class DirectorsController {
    private DirectorsService service;

    @GetMapping
    public ResponseEntity<List<PersonProjection>> getDirectors(@RequestParam(name = "search", required = false) String search) {
        try {
            log.info("Fetching directors. Search: {}", search);
            if (search != null) {
                return ResponseEntity.ok(service.getDirectorsBySearch(search));
            }
            return ResponseEntity.ok(service.getDirectors());
        } catch (Exception e) {
            log.error("An error occurred while fetching directors", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, please try again later");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonMoviesProjection> getDirectorById(@PathVariable int id) {
        try {
            log.info("Fetching director with id: {}", id);
            return ResponseEntity.ok(service.getDirectorById(id));
        } catch (NoSuchElementException e) {
            log.warn("Director with id {} does not exist", id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Director with id " + id + " does not exist.");
        } catch (Exception e) {
            log.error("An error occurred while fetching director with id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }
}
