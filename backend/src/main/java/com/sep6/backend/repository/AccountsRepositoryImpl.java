package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.models.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccountsRepositoryImpl implements AccountsRepository{

    private AccountsJpaRepository jpaRepository;

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
}
