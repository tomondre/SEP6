package com.sep6.backend.repository;

import com.sep6.backend.jpa.PeopleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DirectorsRepositoryImpl implements DirectorsRepository{
    private PeopleJpaRepository jpaRepository;

    @Autowired
    public DirectorsRepositoryImpl(PeopleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
}
