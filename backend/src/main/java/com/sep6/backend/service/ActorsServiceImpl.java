package com.sep6.backend.service;

import com.sep6.backend.repository.ActorsRepository;
import com.sep6.backend.repository.ActorsRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorsServiceImpl implements ActorsService {
    private ActorsRepository repository;
}
