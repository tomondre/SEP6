package com.sep6.backend.service;

import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import com.sep6.backend.repository.ActorsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ActorsServiceImplTest
{
    @Mock
    private ActorsRepository actorsRepository;

    private ActorsService actorsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        actorsService = new ActorsServiceImpl(actorsRepository);
    }

    @Test
    void testGetActorsBySearch() {
        // Arrange
        String search = "John";
        List<PersonProjection> expectedActors = Collections.singletonList(
                mock(PersonProjection.class)
        );
        when(actorsRepository.getActorsBySearch(search)).thenReturn(expectedActors);

        // Act
        List<PersonProjection> result = actorsService.getActorsBySearch(search);

        // Assert
        assertEquals(expectedActors, result);
        verify(actorsRepository).getActorsBySearch(search);
    }

    @Test
    void testGetActors() {
        // Arrange
        List<PersonProjection> expectedActors = Arrays.asList(
                mock(PersonProjection.class),
                mock(PersonProjection.class)
        );
        when(actorsRepository.getActors()).thenReturn(expectedActors);

        // Act
        List<PersonProjection> result = actorsService.getActors();

        // Assert
        assertEquals(expectedActors, result);
        verify(actorsRepository).getActors();
    }

    @Test
    void testGetActorById() {
        // Arrange
        int id = 1;
        PersonMoviesProjection expectedActor = mock(PersonMoviesProjection.class);
        when(actorsRepository.getActorById(id)).thenReturn(Optional.of(expectedActor));

        // Act
        PersonMoviesProjection result = actorsService.getActorById(id);

        // Assert
        assertEquals(expectedActor, result);
        verify(actorsRepository).getActorById(id);
    }
}