package com.sep6.backend.controller;

import com.sep6.backend.models.Account;
import com.sep6.backend.service.AccountsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountsController {

    private AccountsService service;

    @PostMapping(value = "{id}")
    public void editAccount(@PathVariable int id, @RequestBody Account account){
        //TODO: return status codes
        service.editAccount(id, account);
    }

    @DeleteMapping(value = "{id}")
    public void deleteAccount(@PathVariable int id){
        service.deleteAccount(id);
    }
}
