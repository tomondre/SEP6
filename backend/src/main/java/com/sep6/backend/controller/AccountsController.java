package com.sep6.backend.controller;

import com.sep6.backend.models.*;
import com.sep6.backend.projections.AccountProjection;
import com.sep6.backend.projections.FavouriteMovieProjection;
import com.sep6.backend.service.AccountsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import java.util.Set;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@Slf4j
public class AccountsController {

    private AccountsService service;

    @PutMapping(value = "{id}")
    public ResponseEntity<Account> editAccount(@PathVariable int id, @RequestBody AccountDTO account)
    {
        try
        {
            log.info("Editing account with id " + id);
            return ResponseEntity.ok(service.editAccount(id, account));
        }
        catch (NoSuchElementException | IllegalArgumentException e)
        {
            log.warn("Account with id " + id + " does not exist.", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with id " + id + " does not exist. Or the input data is invalid", e);
        }
        catch (Exception e)
        {
            log.error("An error occurred while editing the account with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id){
        try
        {
            log.info("Deleting account with id " + id);
            service.deleteAccount(id);
            return ResponseEntity.ok("Account deleted successfully");
        }
        catch (IllegalArgumentException e)
        {
            log.warn("Account with id " + id + " does not exist.", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with id " + id + " does not exist.", e);
        }
        catch (Exception e)
        {
            log.error("An error occurred while deleting the account with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AccountProjection> getAccountById(@PathVariable int id){
        try
        {
            log.info("Getting account with id " + id);
            return ResponseEntity.ok(service.getAccountById(id));
        }
        catch (NoSuchElementException e)
        {
            log.warn("Account with id " + id + " does not exist.", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with id " + id + " does not exist.", e);
        }
        catch (Exception e)
        {
            log.error("An error occurred while getting the account with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @PostMapping(value = "/{id}/favourites")
    public ResponseEntity<FavouriteRequest> addMovieToAccountFavourites(@PathVariable int id, @RequestBody FavouriteRequest request) {
        try
        {
            log.info("Adding movie with id " + request.getMovieId() + " to account with id " + id);
            request.setAccountId(id);
            return ResponseEntity.ok(service.addMovieToAccountFavourites(request));
        }
        catch (NoSuchElementException e)
        {
            log.warn("Account with id " + id + " does not exist.", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with id " + id + " does not exist.", e);
        }
        catch (Exception e)
        {
            log.error("An error occurred while adding movie with id " + request.getMovieId() + " to account with id " + id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }

    }

    @GetMapping(value = "/{id}/favourites")
    public ResponseEntity<Set<FavouriteMovieProjection>> getAccountFavourites(@PathVariable int id) {
        try
        {
            log.info("Getting favourites for account with id " + id);
            return ResponseEntity.ok(service.getAccountFavourites(id));
        }
        catch (NoSuchElementException e)
        {
            log.warn("Account with id " + id + " does not exist.", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with id " + id + " does not exist.", e);
        }
        catch (Exception e)
        {
            log.error("An error occurred while getting favourites for account with id " + id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @DeleteMapping(value = "/{accountId}/favourites/{movieId}")
    public ResponseEntity<String> deleteAccountFavourite(@PathVariable int accountId, @PathVariable int movieId) {
        try
        {
            log.info("Deleting favourite with id " + movieId + " for account with id " + accountId);
            service.deleteAccountFavourite(accountId, movieId);
            return ResponseEntity.ok("Favourite deleted successfully");
        }
        catch (IllegalArgumentException e)
        {
            log.warn("Account with id " + accountId + " does not exist.", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with id " + accountId + " does not exist.", e);
        }
        catch (Exception e)
        {
            log.error("An error occurred while deleting favourite with id " + movieId + " for account with id " + accountId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<Review>> getAccountReviews(@PathVariable int id) {
        try
        {
            log.info("Getting reviews for account with id " + id);
            return ResponseEntity.ok(service.getAccountReviews(id));
        }
        catch (NoSuchElementException e)
        {
            log.warn("Account with id " + id + " does not exist.", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with id " + id + " does not exist.", e);
        }
        catch (Exception e)
        {
            log.error("An error occurred while getting reviews for account with id " + id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, please try again later");
        }
    }
}
