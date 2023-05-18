package com.sep6.backend.service;

import com.sep6.backend.models.Account;

public interface AccountsService {

    Account editAccount(int id, Account account);
    void deleteAccount(int id);
}
