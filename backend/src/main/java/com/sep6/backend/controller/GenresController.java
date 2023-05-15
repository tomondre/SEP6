package com.sep6.backend.controller;
import com.sep6.backend.models.Genre;
import com.sep6.backend.service.GenresService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@AllArgsConstructor
public class GenresController {
    private GenresService service;

    @GetMapping
    public List<Genre> getAllGenres() {
        return service.getAllGenres();
    }
}
