package com.sep6.backend.controller;

import com.sep6.backend.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private AccountsService service;
    @Autowired
    public AccountsController(AccountsService service) {
        this.service = service;
    }
}
