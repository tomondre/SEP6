package com.sep6.backend.repository;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.AccountDTO;
import com.sep6.backend.models.Review;
import com.sep6.backend.projections.AccountProjection;
import com.sep6.backend.projections.FavouriteMovieProjection;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AccountsRepository {
    Optional<Account> findByEmail(String email);
    Optional<Account> editAccount(int id, AccountDTO account);
    void deleteAccount(int id);

    Account save(Account user);
    Optional<Account> getAccountById(int accountId);
    Account getAccountReferenceById(int id);
    Set<FavouriteMovieProjection> getAccountFavouritesById(int id);
    void addMovieToAccountFavourites(int accountId, int movieId);
    void deleteAccountFavourite(int accountId, int movieId);
    Optional<AccountProjection> getAccountProjectionById(int id);
    List<Review> getAccountReviews(int id);

    Optional<Account> findByUsername(String username);
}
