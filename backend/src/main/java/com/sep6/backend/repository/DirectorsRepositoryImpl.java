package com.sep6.backend.repository;

import com.sep6.backend.jpa.PeopleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DirectorsRepositoryImpl implements DirectorsRepository{
    private PeopleJpaRepository jpaRepository;
}
