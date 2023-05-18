package com.sep6.backend.controller;


import com.sep6.backend.models.Person;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.service.DirectorsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/directors")
@AllArgsConstructor
public class DirectorsController {
    private DirectorsService service;

    @GetMapping
    public ResponseEntity<List<PersonProjection>> getDirectors(@RequestParam(name = "search", required = false) String search) {
      try
      {
          if (search != null)
          {
              return ResponseEntity.ok(service.getDirectorsBySearch(search));
          }
          return ResponseEntity.ok(service.getDirectors());
      }
      catch (Exception e)
      {
          //TODO log the error
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, please try again later");
      }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonMoviesProjection> getDirectorById(@PathVariable int id) {
        try
        {
            return ResponseEntity.ok(service.getDirectorById(id));
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Director with id " + id + " does not exist.");
        }
        catch (Exception e)
        {
            //TODO log the error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }
}
