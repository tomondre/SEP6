package com.sep6.backend.service;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.projections.AccountProjection;
import com.sep6.backend.repository.AccountsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class AccountsServiceImpl implements AccountsService {

    private PasswordEncoder passwordEncoder;
    private AccountsRepository repository;

    @Override
    public FavouriteRequest addMovieToAccountFavourites(FavouriteRequest request) {
        log.info("Adding movie with ID {} to account with ID {}", request.getMovieId(), request.getAccountId());
        repository.addMovieToAccountFavourites(request.getAccountId(), request.getMovieId());
        return request;
    }

    @Override
    public Set<Movie> getAccountFavourites(int id) {
        log.info("Getting account favourites for account with ID {}", id);
        return repository.getAccountFavouritesById(id);
    }

    @Override
    public void deleteAccountFavourite(int accountId, int movieId) {
        log.info("Deleting account favourite for account with ID {} and movie with ID {}", accountId, movieId);
        repository.deleteAccountFavourite(accountId, movieId);
    }

    @Override
    public List<Review> getAccountReviews(int id) {
        log.info("Getting account reviews for account with ID {}", id);
        return repository.getAccountReviews(id);
    }

    @Override
    public AccountProjection getAccountById(int id) {
        log.info("Getting account with ID {}", id);
        return repository.getAccountProjectionById(id).orElseThrow();
    }

    @Override
    public Account editAccount(int id, Account account) {
        log.info("Editing account with ID {}", id);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.editAccount(id, account).orElseThrow();
    }

    @Override
    public void deleteAccount(int id) {
        log.info("Deleting account with ID {}", id);
        repository.deleteAccount(id);
    }
}
