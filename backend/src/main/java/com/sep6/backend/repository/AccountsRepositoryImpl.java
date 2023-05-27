package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.models.Account;
import com.sep6.backend.models.AccountDTO;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.projections.AccountProjection;
import com.sep6.backend.projections.FavouriteMovieProjection;
import com.sep6.backend.util.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AccountsRepositoryImpl implements AccountsRepository{

    private final AccountsJpaRepository jpaRepository;
    private final MoviesRepository movieJpaRepository;
    private final AccountMapper accountMapper;

    @Override
    public Optional<Account> findByEmail(String email) {
        log.info("Finding account by email: {}", email);
        return jpaRepository.findByEmail(email);
    }

    public Optional<Account> editAccount(int id, AccountDTO account) {
        try {
            log.info("Editing account with ID: {}", id);
            Account toEdit = jpaRepository.findById(id).orElseThrow();
            accountMapper.updateAccount(account, toEdit);

            return Optional.of(jpaRepository.save(toEdit));

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
        } catch (Exception e) {
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
    public Set<FavouriteMovieProjection> getAccountFavouritesById(int id) {
        log.info("Getting account favourites by ID: {}", id);
        return jpaRepository.findFavouriteMoviesByAccountId(id);
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
    public Optional<Account> findByUsername(String username)
    {
        return jpaRepository.findByUsername(username);
    }

    @Override
    public Optional<AccountProjection> getAccountProjectionById(int id) {
        log.info("Getting account projection by ID: {}", id);
        return jpaRepository.findByIdAndIdNotNull(id);
    }
}
