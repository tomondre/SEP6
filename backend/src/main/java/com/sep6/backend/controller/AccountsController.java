package com.sep6.backend.controller;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;
import com.sep6.backend.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

import java.util.Set;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountsController {

    private AccountsService service;

    @PutMapping(value = "{id}")
    public ResponseEntity<Account> editAccount(@PathVariable int id, @RequestBody Account account)
    {
        try
        {
            return ResponseEntity.ok(service.editAccount(id, account));
        }
        catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with id " + id + " does not exist.", e);
        }
        catch (Exception e)
        {
            //TODO log the error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id){
        try
        {
            service.deleteAccount(id);
            return ResponseEntity.ok("Account deleted successfully");
        }
        catch (IllegalArgumentException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with id " + id + " does not exist.", e);
        }
        catch (Exception e)
        {
            //TODO log the error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
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
