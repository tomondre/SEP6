package com.sep6.backend.service;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;
import com.sep6.backend.repository.AccountsRepository;
import com.sep6.backend.repository.MoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {
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
}
