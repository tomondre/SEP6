package com.sep6.backend.jpa;

import com.sep6.backend.models.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsJpaRepository extends JpaRepository<Account, Integer>
{
    Optional<Account> findByEmail(String email);
    Optional<Account> findById(int id);

    @Modifying
    @Transactional
    @Query(value = """
      update Account acc\s
      SET acc.isEnabled = false\s
      where acc.id = :id\s
      """)
    void disableAccount(int id);
}
