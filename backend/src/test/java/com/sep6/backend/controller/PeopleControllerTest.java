package com.sep6.backend.controller;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.service.PeopleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PeopleControllerTest
{
    @Mock
    private PeopleService peopleService;

    private PeopleController peopleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        peopleController = new PeopleController(peopleService);
    }
    @Test
    void testGetPeople_NoParams() {
        // Arrange
        List<PersonProjection> people = Arrays.asList(
                mock(PersonProjection.class),
                mock(PersonProjection.class)
        );
        when(peopleService.getPeople()).thenReturn(people);

        // Act
        ResponseEntity<List<PersonProjection>> response = peopleController.getPeople(Optional.empty(), Optional.empty());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(people, response.getBody());
        verify(peopleService, times(1)).getPeople();
    }

    @Test
    void testGetPeople_WithSearchParam() {
        // Arrange
        String searchQuery = "John";
        List<PersonProjection> people = Arrays.asList(
                mock(PersonProjection.class),
                mock(PersonProjection.class)
        );
        when(peopleService.getPeopleBySearch(searchQuery)).thenReturn(people);

        // Act
        ResponseEntity<List<PersonProjection>> response = peopleController.getPeople(Optional.of(searchQuery), Optional.empty());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(people, response.getBody());
        verify(peopleService, times(1)).getPeopleBySearch(searchQuery);
    }

    @Test
    void testGetPeople_WithPageParam() {
        // Arrange
        int page = 2;
        List<PersonProjection> people = Arrays.asList(
                mock(PersonProjection.class),
                mock(PersonProjection.class)
        );
        when(peopleService.getPaginatedPeople(page)).thenReturn(people);

        // Act
        ResponseEntity<List<PersonProjection>> response = peopleController.getPeople(Optional.empty(), Optional.of(page));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(people, response.getBody());
        verify(peopleService, times(1)).getPaginatedPeople(page);
    }

    @Test
    void testGetPersonById() {
        // Arrange
        int personId = 1;
        PersonMoviesProjection person = mock(PersonMoviesProjection.class);
        when(peopleService.getPersonById(personId)).thenReturn(person);

        // Act
        ResponseEntity<PersonMoviesProjection> response = peopleController.getPersonById(personId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(person, response.getBody());
        verify(peopleService, times(1)).getPersonById(personId);
    }

    @Test
    void testGetPersonById_NonexistentPerson() {
        // Arrange
        int personId = 999;
        when(peopleService.getPersonById(personId)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> peopleController.getPersonById(personId));
    }

    @Test
    void testGetPersonById_ExceptionInService() {
        // Arrange
        int personId = 1;
        when(peopleService.getPersonById(personId)).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> peopleController.getPersonById(personId));
    }
}