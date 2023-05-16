package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.models.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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

    public Optional<Account> editAccount(int id, Account account)
    {
        Optional<Account> toEdit = jpaRepository.findById(id);

        if (toEdit.isPresent()){
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
    }

    @Override
    public void deleteAccount(int id)
    {
        jpaRepository.disableAccount(id);
    }

    @Override
    public Account save(Account user)
    {
        return jpaRepository.save(user);
    }

    @Override
    public Account getAccountById(int accountId) {
        return jpaRepository.findById(accountId);
    }
}
