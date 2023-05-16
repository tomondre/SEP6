package com.sep6.backend.repository;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.Movie;

import java.util.Optional;
import java.util.Set;

public interface AccountsRepository {
    Optional<Account> findByEmail(String email);
    Optional<Account> editAccount(int id, Account account);
    void deleteAccount(int id);

    Account save(Account user);
    Account getAccountById(int accountId);
    Account getAccountReferenceById(int id);
    Set<Movie> getAccountFavouritesById(int id);
    void addMovieToAccountFavourites(int accountId, int movieId);
    void deleteAccountFavourite(int accountId, int movieId);
}
