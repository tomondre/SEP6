package com.sep6.backend.service;

import com.sep6.backend.models.Movie;
import com.sep6.backend.projections.MovieProjection;
import com.sep6.backend.repository.MoviesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class MoviesServiceImplTest
{
    @Mock
    private MoviesRepository repository;

    private MoviesServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new MoviesServiceImpl(repository);
    }

    @Test
    public void testCreateMovie() {
        // Arrange
        Movie movie = new Movie();
        when(repository.createMovie(movie)).thenReturn(movie);

        // Act
        Movie result = service.createMovie(movie);

        // Assert
        assertEquals(movie, result);
        verify(repository, times(1)).createMovie(movie);
    }

    @Test
    public void testGetMovies() {
        // Arrange
        List<MovieProjection> movies = new ArrayList<>();
        when(repository.getMovies()).thenReturn(movies);

        // Act
        List<MovieProjection> result = service.getMovies();

        // Assert
        assertEquals(movies, result);
        verify(repository, times(1)).getMovies();
    }

    @Test
    public void testGetMoviesBySearch() {
        // Arrange
        String search = "example";
        List<MovieProjection> movies = new ArrayList<>();
        when(repository.getMoviesBySearch(search)).thenReturn(movies);

        // Act
        List<MovieProjection> result = service.getMoviesBySearch(search);

        // Assert
        assertEquals(movies, result);
        verify(repository, times(1)).getMoviesBySearch(search);
    }

    @Test
    public void testGetMoviesByGenreId() {
        // Arrange
        int genreId = 1;
        List<MovieProjection> movies = new ArrayList<>();
        when(repository.getMoviesByGenreId(genreId)).thenReturn(movies);

        // Act
        List<MovieProjection> result = service.getMoviesByGenreId(genreId);

        // Assert
        assertEquals(movies, result);
        verify(repository, times(1)).getMoviesByGenreId(genreId);
    }

    @Test
    public void testGetMovieById() {
        // Arrange
        int id = 1;
        Movie movie = new Movie();
        when(repository.getMovieById(id)).thenReturn(Optional.of(movie));

        // Act
        Movie result = service.getMovieById(id);

        // Assert
        assertEquals(movie, result);
        verify(repository, times(1)).getMovieById(id);
    }

    @Test
    public void testGetMovieById_NotFound() {
        // Arrange
        int id = 1;
        when(repository.getMovieById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> service.getMovieById(id));
        verify(repository, times(1)).getMovieById(id);
    }

    @Test
    public void testGetLatestMovies() {
        // Arrange
        int actualLimit = 10;
        List<MovieProjection> movies = new ArrayList<>();
        when(repository.getLatestMovies(actualLimit)).thenReturn(movies);

        // Act
        List<MovieProjection> result = service.getLatestMovies(actualLimit);

        // Assert
        assertEquals(movies, result);
        verify(repository, times(1)).getLatestMovies(actualLimit);
    }

    @Test
    public void testGetPaginatedMovies() {
        // Arrange
        int pageInt = 1;
        List<MovieProjection> movies = new ArrayList<>();
        when(repository.getPaginatedMovies(pageInt)).thenReturn(movies);

        // Act
        List<MovieProjection> result = service.getPaginatedMovies(pageInt);

        // Assert
        assertEquals(movies, result);
        verify(repository, times(1)).getPaginatedMovies(pageInt);
    }
}