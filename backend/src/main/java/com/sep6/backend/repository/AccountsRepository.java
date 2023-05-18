package com.sep6.backend.repository;

import com.sep6.backend.exceptions.EditException;
import com.sep6.backend.exceptions.SaveException;
import com.sep6.backend.models.Account;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.FetchNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

public interface AccountsRepository {
    Optional<Account> findByEmail(String email);
    Optional<Account> editAccount(int id, Account account) throws AccountNotFoundException, IllegalArgumentException;
    void deleteAccount(int id) throws IllegalArgumentException;

    Account save(Account user) throws IllegalArgumentException;
    Optional<Account> getAccountById(int accountId);
}
