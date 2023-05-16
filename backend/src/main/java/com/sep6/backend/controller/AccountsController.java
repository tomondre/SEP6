package com.sep6.backend.controller;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;
import com.sep6.backend.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountsController {

    private AccountsService service;

    @PutMapping(value = "{id}")
    public void editAccount(@PathVariable int id, @RequestBody Account account){
        service.editAccount(id, account);
    }

    @DeleteMapping(value = "{id}")
    public void deleteAccount(@PathVariable int id){
        service.deleteAccount(id);
    }

    @PostMapping(value = "/{id}/favourites")
    public FavouriteRequest addMovieToAccountFavourites(@PathVariable int id, @RequestBody FavouriteRequest request) {
        request.setAccountId(id);
        return service.addMovieToAccountFavourites(request);
    }

    @GetMapping(value = "/{id}/favourites")
    public Set<Movie> getAccountFavourites(@PathVariable int id) {
        return service.getAccountFavourites(id);
    }

    @DeleteMapping(value = "/{accountId}/favourites/{movieId}")
    public void deleteAccountFavourite(@PathVariable int accountId, @PathVariable int movieId) {
        service.deleteAccountFavourite(accountId, movieId);
    }
}
