package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.models.Account;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.projections.AccountProjection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
@Slf4j
public class AccountsRepositoryImpl implements AccountsRepository{

    private AccountsJpaRepository jpaRepository;
    private MoviesRepository movieJpaRepository;

    @Override
    public Optional<Account> findByEmail(String email) {
        log.info("Finding account by email: {}", email);
        return jpaRepository.findByEmail(email);
    }

    public Optional<Account> editAccount(int id, Account account) {
        try {
            log.info("Editing account with ID: {}", id);
            Account edited = jpaRepository.findById(id).orElseThrow();

            edited.setUsername(account.getUsername());
            edited.setPassword(account.getPassword());
            edited.setEmail(account.getEmail());
            edited.setName(account.getName());
            edited.setCountry(account.getCountry());
            edited.setProfilePictureUrl(account.getProfilePictureUrl());
            return Optional.of(jpaRepository.save(edited));

        } catch (NoSuchElementException e) {
            log.error("Account not found with ID: {}", id);
            throw new NoSuchElementException("Account not found with ID: " + id);
        } catch (IllegalArgumentException e) {
            log.error("Failed to edit account due to missing data: {}", e.getMessage(), e);
            throw new IllegalArgumentException("Failed to edit account, because of missing data.", e);
        }
    }

    @Override
    public void deleteAccount(int id) {
        try {
            log.info("Deleting account with ID: {}", id);
            jpaRepository.disableAccount(id);
        } catch (Exception e) {
            log.error("Failed to delete account with ID: {}", id, e);
            throw new IllegalArgumentException("Failed to delete account.", e);
        }
    }

    @Override
    public Account save(Account user) {
        try {
            log.info("Saving account: {}", user);
            return jpaRepository.save(user);
        } catch (IllegalArgumentException e) {
            log.error("Failed to save account: {}", user, e);
            throw new IllegalArgumentException("Failed to save account.", e);
        }
    }

    @Override
    public Optional<Account> getAccountById(int accountId) {
        log.info("Getting account by ID: {}", accountId);
        return jpaRepository.findById(accountId);
    }

    @Override
    public Account getAccountReferenceById(int id) {
        log.info("Getting account reference by ID: {}", id);
        return jpaRepository.getReferenceById(id);
    }

    @Override
    public Set<Movie> getAccountFavouritesById(int id) {
        log.info("Getting account favourites by ID: {}", id);
        Account account = getAccountById(id).orElseThrow();
        return account.getFavourites();
    }

    public void addMovieToAccountFavourites(int accountId, int movieId) {
        log.info("Adding movie with ID {} to account favourites with ID {}", movieId, accountId);
        Movie movieReferenceById = movieJpaRepository.getMovieReferenceById(movieId);
        Account referenceById = jpaRepository.getReferenceById(accountId);

        referenceById.getFavourites().add(movieReferenceById);
        movieReferenceById.getFavouredBy().add(referenceById);

        jpaRepository.save(referenceById);
    }

    @Override
    public void deleteAccountFavourite(int accountId, int movieId) {
        log.info("Deleting movie with ID {} from account favourites with ID {}", movieId, accountId);
        Movie movieReferenceById = movieJpaRepository.getMovieReferenceById(movieId);
        Account accountReferenceById = jpaRepository.getReferenceById(accountId);

        movieReferenceById.getFavouredBy().remove(accountReferenceById);
        accountReferenceById.getFavourites().remove(movieReferenceById);

        jpaRepository.save(accountReferenceById);
    }

    @Override
    public List<Review> getAccountReviews(int id) {
        log.info("Getting reviews for account with ID: {}", id);
        return getAccountById(id).orElseThrow().getReviews();
    }

    @Override
    public Optional<AccountProjection> getAccountProjectionById(int id) {
        log.info("Getting account projection by ID: {}", id);
        return jpaRepository.findByIdAndIdNotNull(id);
    }
}
