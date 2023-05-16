package com.sep6.backend.service;

import com.sep6.backend.models.Account;
import com.sep6.backend.repository.AccountsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private PasswordEncoder passwordEncoder;
    private AccountsRepository repository;

    @Override
    public Optional<Account> editAccount(int id, Account account)
    {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return repository.editAccount(id, account);
    }

    @Override
    public void deleteAccount(int id)
    {
        repository.deleteAccount(id);
    }
}
