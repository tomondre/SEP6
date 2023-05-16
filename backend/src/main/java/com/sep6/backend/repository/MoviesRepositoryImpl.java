package com.sep6.backend.repository;

import com.sep6.backend.jpa.GenresJpaRepository;
import com.sep6.backend.jpa.MoviesJpaRepository;
import com.sep6.backend.jpa.PeopleJpaRepository;
import com.sep6.backend.models.Genre;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Person;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MoviesRepositoryImpl implements MoviesRepository{
    private MoviesJpaRepository jpaRepository;
    private PeopleJpaRepository peopleJpaRepository;
    private GenresJpaRepository genresJpaRepository;

    @Override
    public Movie createMovie(Movie movie) {
        List<Person> people = movie.getPeople();
        for (Person person : people) {
            Optional<Person> byId = peopleJpaRepository.findById(person.getId());
            if (byId.isEmpty()) {
                peopleJpaRepository.save(person);
            }
        }

        List<Genre> genres = movie.getGenres();
        for (Genre genre : genres) {
            Optional<Genre> byId = genresJpaRepository.findById(genre.getId());
            if (byId.isEmpty()) {
                genresJpaRepository.save(genre);
            }
        }

        return jpaRepository.save(movie);
    }

    @Override
    public List<Movie> getMovies() {
        return jpaRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesBySearch(String search) {
        return jpaRepository.findByTitleContainingIgnoreCase(search);
    }

    @Override
    public List<Movie> getMoviesByGenreId(int genreId) {
        return jpaRepository.findByGenresId(genreId);
    }

    @Override
    public Movie getMovieById(int id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Movie> getLatestMovies(int actualLimit) {
        Pageable pageable = PageRequest.of(0, actualLimit);
        return jpaRepository.findAllByOrderByReleaseDateDesc(pageable);
    }

    @Override
    public List<Movie> getPaginatedMovies(int pageInt) {
        PageRequest page = PageRequest.of(pageInt, 10);
        return jpaRepository.findAll(page).getContent();
    }

    @Override
    public Movie updateMovieRatingById(int movieId, double rating) {
        Movie movieById = getMovieById(movieId);
        movieById.setRating(rating);
        return jpaRepository.save(movieById);
    }

    @Override
    public Movie getMovieByIdReference(int movieId) {
        return jpaRepository.getReferenceById(movieId);
    }
}
