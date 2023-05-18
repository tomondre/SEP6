package com.sep6.backend.service;

import com.sep6.backend.models.Account;

import java.util.Set;

import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;

public interface AccountsService {

    Account editAccount(int id, Account account);
    void deleteAccount(int id);
    FavouriteRequest addMovieToAccountFavourites(FavouriteRequest request);
    Set<Movie> getAccountFavourites(int id);
    void deleteAccountFavourite(int accountId, int movieId);
}
