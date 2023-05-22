package com.sep6.backend.service;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.repository.DirectorsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class DirectorsServiceImplTest
{
    private DirectorsServiceImpl directorsService;

    @Mock
    private DirectorsRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        directorsService = new DirectorsServiceImpl(repository);
    }

    @Test
    void testGetDirectorsBySearch() {
        // Arrange
        String searchTerm = "John";
        List<PersonProjection> expectedDirectors = Collections.singletonList(mock(PersonProjection.class));
        when(repository.getDirectorsBySearch(searchTerm)).thenReturn(expectedDirectors);

        // Act
        List<PersonProjection> result = directorsService.getDirectorsBySearch(searchTerm);

        // Assert
        assertEquals(expectedDirectors, result);
        verify(repository, times(1)).getDirectorsBySearch(searchTerm);
    }

    @Test
    void testGetDirectors() {
        // Arrange
        List<PersonProjection> expectedDirectors = Collections.singletonList(mock(PersonProjection.class));
        when(repository.getDirectors()).thenReturn(expectedDirectors);

        // Act
        List<PersonProjection> result = directorsService.getDirectors();

        // Assert
        assertEquals(expectedDirectors, result);
        verify(repository, times(1)).getDirectors();
    }

    @Test
    void testGetDirectorById() {
        // Arrange
        int directorId = 1;
        PersonMoviesProjection expectedDirector = mock(PersonMoviesProjection.class);
        when(repository.getDirectorsById(directorId)).thenReturn(Optional.of(expectedDirector));

        // Act
        PersonMoviesProjection result = directorsService.getDirectorById(directorId);

        // Assert
        assertEquals(expectedDirector, result);
        verify(repository, times(1)).getDirectorsById(directorId);
    }
}