package com.sep6.backend.controller;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.service.PeopleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
@AllArgsConstructor
@Slf4j
public class PeopleController {
    private PeopleService service;

    @GetMapping
    public ResponseEntity<List<PersonProjection>> getPeople(
            @RequestParam(name = "search", required = false) Optional<String> search,
            @RequestParam(name = "page", required = false) Optional<Integer> page
    ) {
        try {
            if (page.isPresent()) {
                log.info("Fetching paginated people: page {}", page.get());
                return ResponseEntity.ok(service.getPaginatedPeople(page.get()));
            }
            if (search.isPresent() && !search.get().isEmpty()) {
                log.info("Fetching people by search: {}", search.get());
                return ResponseEntity.ok(service.getPeopleBySearch(search.get()));
            }

            log.info("Fetching all people");
            return ResponseEntity.ok(service.getPeople());
        } catch (Exception e) {
            log.error("Error occurred while fetching people", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, please try again later");
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonMoviesProjection> getPersonById(@PathVariable int id) {
        try {
            log.info("Fetching person by id: {}", id);
            return ResponseEntity.ok(service.getPersonById(id));
        } catch (NullPointerException e) {
            log.error("Person with id {} does not exist", id, e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person with id " + id + " does not exist.", e);
        } catch (Exception e) {
            log.error("Error occurred while fetching person", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, please try again later");
        }
    }

}
