package com.sep6.backend.controller;
import com.sep6.backend.service.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenresController {
    private GenresService service;

    @Autowired
    public GenresController(GenresService service) {
        this.service = service;
    }
}
