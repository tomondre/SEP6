package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.models.Account;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class AccountsRepositoryImpl implements AccountsRepository{

    private AccountsJpaRepository jpaRepository;
    private MoviesRepository movieJpaRepository;

    @Override
    public Optional<Account> findByEmail(String email)
    {
       return jpaRepository.findByEmail(email);
    }

    public Optional<Account> editAccount(int id, Account account)
    {
        try {
            Account edited = jpaRepository.findById(id).orElseThrow();

            edited.setEmail(account.getEmail());
            edited.setName(account.getName());
            edited.setCountry(account.getCountry());
            edited.setProfilePictureUrl(account.getProfilePictureUrl());
            edited.setPassword(account.getPassword());
            edited.setEnabled(account.isEnabled());
            edited.setFavourites(account.getFavourites());
            return Optional.of(jpaRepository.save(edited));

        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Account not found with ID: " + id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to edit account, because of missing data.", e);
        }
    }

    @Override
    public void deleteAccount(int id)
    {
        try
        {
            jpaRepository.disableAccount(id);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Failed to delete account.", e);
        }
    }

    @Override
    public Account save(Account user)
    {
        try
        {
            return jpaRepository.save(user);
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Failed to save account.", e);
        }
    }

    @Override
    public Optional<Account> getAccountById(int accountId)
    {
        return jpaRepository.findById(accountId);
    }

    @Override
    public Account getAccountReferenceById(int id) {
        return jpaRepository.getReferenceById(id);
    }

    @Override
    public Set<Movie> getAccountFavouritesById(int id) {
        Account account = getAccountById(id).orElseThrow();
        return account.getFavourites();
    }

    public void addMovieToAccountFavourites(int accountId, int movieId) {
        Movie movieReferenceById = movieJpaRepository.getMovieReferenceById(movieId);
        Account referenceById = jpaRepository.getReferenceById(accountId);

        referenceById.getFavourites().add(movieReferenceById);
        movieReferenceById.getFavouredBy().add(referenceById);

        jpaRepository.save(referenceById);
    }

    @Override
    public void deleteAccountFavourite(int accountId, int movieId) {
        Movie movieReferenceById = movieJpaRepository.getMovieReferenceById(movieId);
        Account accountReferenceById = jpaRepository.getReferenceById(accountId);

        movieReferenceById.getFavouredBy().remove(accountReferenceById);
        accountReferenceById.getFavourites().remove(movieReferenceById);

        jpaRepository.save(accountReferenceById);
    }

    @Override
    public List<Review> getAccountReviews(int id) {
        return getAccountById(id).orElseThrow().getReviews();
    }
}
