package com.sep6.backend.repository;

import com.sep6.backend.jpa.AccountsJpaRepository;
import com.sep6.backend.jpa.PeopleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ActorsRepositoryImpl implements ActorsRepository{
    private PeopleJpaRepository jpaRepository;
}
