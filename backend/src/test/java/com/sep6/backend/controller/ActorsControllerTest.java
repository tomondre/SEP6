package com.sep6.backend.controller;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.service.ActorsService;
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
class ActorsControllerTest
{
    @Mock
    private ActorsService actorsService;

    private ActorsController actorsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        actorsController = new ActorsController(actorsService);
    }

    @Test
    void testGetActors_WithoutSearchQuery_ReturnsListOfActors() {
        // Arrange
        List<PersonProjection> actors = new ArrayList<>();
        when(actorsService.getActors()).thenReturn(actors);

        // Act
        ResponseEntity<List<PersonProjection>> response = actorsController.getActors(null);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(actors, response.getBody());
    }

    @Test
    void testGetActors_WithSearchQuery_ReturnsFilteredListOfActors() {
        // Arrange
        String searchQuery = "John";
        List<PersonProjection> actors = new ArrayList<>();
        when(actorsService.getActorsBySearch(searchQuery)).thenReturn(actors);

        // Act
        ResponseEntity<List<PersonProjection>> response = actorsController.getActors(searchQuery);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(actors, response.getBody());
    }

    @Test
    void testGetActorById_ExistingActorId_ReturnsActor() {
        // Arrange
        int actorId = 1;
        PersonMoviesProjection actor = mock(PersonMoviesProjection.class);
        when(actorsService.getActorById(actorId)).thenReturn(actor);

        // Act
        ResponseEntity<PersonMoviesProjection> response = actorsController.getActorById(actorId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(actor, response.getBody());
    }

    @Test
    void testGetActorById_NonExistingActorId_ThrowsBadRequestException() {
        // Arrange
        int actorId = 1;
        when(actorsService.getActorById(actorId)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> actorsController.getActorById(actorId));
    }
}