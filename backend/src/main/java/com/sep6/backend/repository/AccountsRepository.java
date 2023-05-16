package com.sep6.backend.repository;

import com.sep6.backend.models.Account;

import java.util.Optional;

public interface AccountsRepository {
    Optional<Account> findByEmail(String email);
    Account save(Account user);
    Account getAccountById(int accountId);
}
