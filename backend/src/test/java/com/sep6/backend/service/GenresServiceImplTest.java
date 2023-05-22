package com.sep6.backend.service;

import com.sep6.backend.models.Genre;
import com.sep6.backend.repository.GenresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GenresServiceImplTest
{
    private GenresServiceImpl genresService;

    @Mock
    private GenresRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        genresService = new GenresServiceImpl(repository);
    }

    @Test
    void testGetAllGenres() {
        // Arrange
        List<Genre> expectedGenres = Arrays.asList(
                Genre.builder().id(1).name("Action").build(),
                Genre.builder().id(2).name("Adventure").build(),
                Genre.builder().id(3).name("Animation").build()
        );
        when(repository.getAllGenres()).thenReturn(expectedGenres);

        // Act
        List<Genre> result = genresService.getAllGenres();

        // Assert
        assertEquals(expectedGenres, result);
        verify(repository, times(1)).getAllGenres();
    }

    @Test
    void testSave() {
        // Arrange
        Genre genre = Genre.builder().id(1).name("Action").build();
        when(repository.save(genre)).thenReturn(genre);

        // Act
        Genre result = genresService.save(genre);

        // Assert
        assertEquals(genre, result);
        verify(repository, times(1)).save(genre);
    }
}