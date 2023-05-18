package com.sep6.backend.repository;

import com.sep6.backend.exceptions.EditException;
import com.sep6.backend.exceptions.SaveException;
import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.models.Account;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.FetchNotFoundException;
import org.springframework.stereotype.Repository;

import javax.security.auth.login.AccountNotFoundException;
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

    public Optional<Account> editAccount(int id, Account account) throws AccountNotFoundException, IllegalArgumentException
    {
        try {
            Optional<Account> toEdit = jpaRepository.findById(id);

            if (toEdit.isPresent()) {
                Account edited = toEdit.get();
                edited.setEmail(account.getEmail());
                edited.setName(account.getName());
                edited.setCountry(account.getCountry());
                edited.setProfilePictureUrl(account.getProfilePictureUrl());
                edited.setPassword(account.getPassword());
                edited.setEnabled(account.isEnabled());
                return Optional.of(jpaRepository.save(edited));
            }
            return Optional.empty();
        } catch (NoSuchElementException e) {
            throw new AccountNotFoundException("Account not found with ID: " + id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to edit account, because of missing data.", e);
        }
    }

    @Override
    public void deleteAccount(int id) throws IllegalArgumentException
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
    public Account save(Account user) throws IllegalArgumentException
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
