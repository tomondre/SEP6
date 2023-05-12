package com.sep6.backend.service;

import com.sep6.backend.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsServiceImpl implements AccountsService {
    private AccountsRepository repository;

    @Autowired
    public AccountsServiceImpl(AccountsRepository repository) {
        this.repository = repository;
    }
}
