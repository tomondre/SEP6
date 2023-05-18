package com.sep6.backend.controller;

import com.sep6.backend.models.Genre;
import com.sep6.backend.service.GenresService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/genres")
@AllArgsConstructor
public class GenresController {
    private GenresService service;

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        try
        {
            return ResponseEntity.ok(service.getAllGenres());
        }
        catch (Exception e)
        {
            //TODO log the error
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong, please try again later");
        }
    }
}
