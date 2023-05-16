package com.sep6.backend.service;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Account;
import com.sep6.backend.repository.AccountsRepository;
import com.sep6.backend.repository.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private PasswordEncoder passwordEncoder;
    private AccountsRepository repository;
    private MoviesRepository moviesRepository;

    @Override
    public FavouriteRequest addMovieToAccountFavourites(FavouriteRequest request) {
        Movie movieByIdReference = moviesRepository.getMovieByIdReference(request.getMovieId());
        Account accountById = repository.getAccountById(request.getAccountId());
        accountById.getFavourites().add(movieByIdReference);
        repository.updateAccount(accountById);
        return request;
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
