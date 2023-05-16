package com.sep6.backend.repository;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import java.util.Optional;

public interface AccountsRepository {
    Optional<Account> findByEmail(String email);
    Optional<Account> editAccount(int id, Account account);
    void deleteAccount(int id);

    Account save(Account user);
    Account getAccountById(int accountId);
}
