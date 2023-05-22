package com.sep6.backend.service;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PeopleServiceImplTest
{
    @Mock
    private PeopleRepository repository;

    private PeopleService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new PeopleServiceImpl(repository);
    }

    @Test
    public void testGetPeopleBySearch() {
        // Arrange
        String search = "John";
        List<PersonProjection> people = new ArrayList<>();
        when(repository.getPeopleBySearch(search)).thenReturn(people);

        // Act
        List<PersonProjection> result = service.getPeopleBySearch(search);

        // Assert
        assertEquals(people, result);
        verify(repository, times(1)).getPeopleBySearch(search);
    }

    @Test
    public void testGetPeople() {
        // Arrange
        List<PersonProjection> people = new ArrayList<>();
        when(repository.getPeople()).thenReturn(people);

        // Act
        List<PersonProjection> result = service.getPeople();

        // Assert
        assertEquals(people, result);
        verify(repository, times(1)).getPeople();
    }

    @Test
    public void testGetPersonById() {
        // Arrange
        int id = 1;
        PersonMoviesProjection person = mock(PersonMoviesProjection.class);
        when(repository.getPersonById(id)).thenReturn(Optional.of(person));

        // Act
        PersonMoviesProjection result = service.getPersonById(id);

        // Assert
        assertEquals(person, result);
        verify(repository, times(1)).getPersonById(id);
    }

    @Test
    public void testGetPaginatedPeople() {
        // Arrange
        int page = 1;
        List<PersonProjection> people = new ArrayList<>();
        when(repository.getPaginatedPeople(page)).thenReturn(people);

        // Act
        List<PersonProjection> result = service.getPaginatedPeople(page);

        // Assert
        assertEquals(people, result);
        verify(repository, times(1)).getPaginatedPeople(page);
    }
}