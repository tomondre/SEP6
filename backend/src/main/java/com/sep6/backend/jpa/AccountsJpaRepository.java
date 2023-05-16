package com.sep6.backend.jpa;

import com.sep6.backend.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsJpaRepository extends JpaRepository<Account, Integer>
{
    Optional<Account> findByEmail(String email);
    Account findById(int id);
}
