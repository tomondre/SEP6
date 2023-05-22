package com.sep6.backend.controller;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.service.DirectorsService;
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
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DirectorsControllerTest
{
    @Mock
    private DirectorsService directorsService;

    private DirectorsController directorsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        directorsController = new DirectorsController(directorsService);
    }

    @Test
    void testGetDirectors_WithoutSearchQuery_ReturnsListOfDirectors() {
        // Arrange
        List<PersonProjection> directors = new ArrayList<>();
        when(directorsService.getDirectors()).thenReturn(directors);

        // Act
        ResponseEntity<List<PersonProjection>> response = directorsController.getDirectors(null);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(directors, response.getBody());
    }

    @Test
    void testGetDirectors_WithSearchQuery_ReturnsFilteredListOfDirectors() {
        // Arrange
        String searchQuery = "Christopher";
        List<PersonProjection> directors = new ArrayList<>();
        when(directorsService.getDirectorsBySearch(searchQuery)).thenReturn(directors);

        // Act
        ResponseEntity<List<PersonProjection>> response = directorsController.getDirectors(searchQuery);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(directors, response.getBody());
    }

    @Test
    void testGetDirectorById_ExistingDirectorId_ReturnsDirector() {
        // Arrange
        int directorId = 1;
        PersonMoviesProjection director = mock(PersonMoviesProjection.class);
        when(directorsService.getDirectorById(directorId)).thenReturn(director);

        // Act
        ResponseEntity<PersonMoviesProjection> response = directorsController.getDirectorById(directorId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(director, response.getBody());
    }

    @Test
    void testGetDirectorById_NonExistingDirectorId_ThrowsBadRequestException() {
        // Arrange
        int directorId = 1;
        when(directorsService.getDirectorById(directorId)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> directorsController.getDirectorById(directorId));
    }
}