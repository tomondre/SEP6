package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.models.Account;
import com.sep6.backend.models.Movie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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
        Optional<Account> toEdit = jpaRepository.findById(id);

        if (toEdit.isPresent()){
            Account edited = toEdit.get();
            edited.setEmail(account.getEmail());
            edited.setName(account.getName());
            edited.setCountry(account.getCountry());
            edited.setProfilePictureUrl(account.getProfilePictureUrl());
            edited.setPassword(account.getPassword());
            edited.setEnabled(account.isEnabled());
            edited.setFavourites(account.getFavourites());
            return Optional.of(jpaRepository.save(edited));
        }
        return Optional.empty();
    }

    @Override
    public void deleteAccount(int id)
    {
        jpaRepository.disableAccount(id);
    }

    @Override
    public Account save(Account user)
    {
        return jpaRepository.save(user);
    }

    @Override
    public Account getAccountById(int accountId) {
        return jpaRepository.findById(accountId).get();
    }

    @Override
    public Account getAccountReferenceById(int id) {
        return jpaRepository.getReferenceById(id);
    }

    @Override
    public Set<Movie> getAccountFavouritesById(int id) {
        Account account = getAccountById(id);
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
}
