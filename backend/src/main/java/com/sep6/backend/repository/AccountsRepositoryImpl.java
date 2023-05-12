package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountsRepositoryImpl implements AccountsRepository{

    private AccountsJpaRepository jpaRepository;

    @Autowired
    public AccountsRepositoryImpl(AccountsJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
}
