package com.sep6.backend.service;

import com.sep6.backend.models.Account;

import java.util.Optional;

public interface AccountsService {

    Optional<Account> editAccount(int id, Account account);
    void deleteAccount(int id);
}
