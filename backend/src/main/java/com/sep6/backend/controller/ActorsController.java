package com.sep6.backend.controller;
import com.sep6.backend.service.ActorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actors")
public class ActorsController {
    private ActorsService service;

    @Autowired
    public ActorsController(ActorsService service) {
        this.service = service;
    }
}
