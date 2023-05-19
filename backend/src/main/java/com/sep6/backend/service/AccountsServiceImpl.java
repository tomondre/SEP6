package com.sep6.backend.service;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.projections.AccountProjection;
import com.sep6.backend.repository.AccountsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private PasswordEncoder passwordEncoder;
    private AccountsRepository repository;

    @Override
    public FavouriteRequest addMovieToAccountFavourites(FavouriteRequest request) {
        repository.addMovieToAccountFavourites(request.getAccountId(), request.getMovieId());
        return request;
    }

    @Override
    public Set<Movie> getAccountFavourites(int id) {
        return repository.getAccountFavouritesById(id);
    }

    @Override
    public void deleteAccountFavourite(int accountId, int movieId) {
        repository.deleteAccountFavourite(accountId, movieId);
    }

    @Override
    public List<Review> getAccountReviews(int id) {
        return repository.getAccountReviews(id);
    }

    @Override
    public AccountProjection getAccountById(int id) {
        return repository.getAccountProjectionById(id).orElseThrow();
    }

    @Override
    public Account editAccount(int id, Account account)
    {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.editAccount(id, account).orElseThrow();
    }

    @Override
    public void deleteAccount(int id)
    {
        repository.deleteAccount(id);
    }
}
