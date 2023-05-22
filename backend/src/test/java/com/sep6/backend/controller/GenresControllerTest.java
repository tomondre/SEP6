package com.sep6.backend.controller;

import com.sep6.backend.models.Genre;
import com.sep6.backend.service.GenresService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GenresControllerTest
{
    @Mock
    private GenresService genresService;

    private GenresController genresController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        genresController = new GenresController(genresService);
    }

    @Test
    void testGetAllGenres_ReturnsListOfGenres() {
        // Arrange
        List<Genre> genres = new ArrayList<>();
        when(genresService.getAllGenres()).thenReturn(genres);

        // Act
        ResponseEntity<List<Genre>> response = genresController.getAllGenres();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(genres, response.getBody());
    }
}