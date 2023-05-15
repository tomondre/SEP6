package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountsRepositoryImpl implements AccountsRepository{

    private AccountsJpaRepository jpaRepository;

    @Override
    public Optional<Account> findByEmail(String email)
    {
        return jpaRepository.findByEmail(email);
    }

    @Override
    public Account save(Account user)
    {
        return jpaRepository.save(user);
    }
}
