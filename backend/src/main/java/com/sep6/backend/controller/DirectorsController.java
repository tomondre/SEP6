package com.sep6.backend.controller;


import com.sep6.backend.service.DirectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/directors")
public class DirectorsController {
    private DirectorsService service;

    @Autowired
    public DirectorsController(DirectorsService service) {
        this.service = service;
    }
}
