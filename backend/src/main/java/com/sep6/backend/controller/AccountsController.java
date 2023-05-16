package com.sep6.backend.controller;

import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    private AccountsService service;
    @Autowired
    public AccountsController(AccountsService service) {
        this.service = service;
    }

    @PostMapping(value = "/{id}/favourites")
    public FavouriteRequest addMovieToAccountFavourites(@PathVariable int id, @RequestBody FavouriteRequest request) {
        request.setAccountId(id);
        return service.addMovieToAccountFavourites(request);
    }
}
