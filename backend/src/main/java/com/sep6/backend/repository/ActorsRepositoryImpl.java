package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.jpa.PeopleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ActorsRepositoryImpl implements ActorsRepository{
    private PeopleJpaRepository jpaRepository;

    @Autowired
    public ActorsRepositoryImpl(PeopleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
}
