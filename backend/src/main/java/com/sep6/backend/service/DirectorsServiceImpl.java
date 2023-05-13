package com.sep6.backend.service;


import com.sep6.backend.repository.ActorsRepository;
import com.sep6.backend.repository.DirectorsRepository;
import com.sep6.backend.repository.DirectorsRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorsServiceImpl implements DirectorsService {
    private DirectorsRepository repository;

}
