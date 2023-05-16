package com.sep6.backend.service;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;
import com.sep6.backend.repository.AccountsRepository;
import com.sep6.backend.repository.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private PasswordEncoder passwordEncoder;
    private AccountsRepository repository;
    private MoviesRepository moviesRepository;

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
    public Optional<Account> editAccount(int id, Account account)
    {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.editAccount(id, account);
    }

    @Override
    public void deleteAccount(int id)
    {
        repository.deleteAccount(id);
    }
}
