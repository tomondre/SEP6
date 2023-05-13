package com.sep6.backend.repository;

import com.sep6.backend.jpa.GenresJpaRepository;
import com.sep6.backend.jpa.MoviesJpaRepository;
import com.sep6.backend.jpa.PeopleJpaRepository;
import com.sep6.backend.models.Genre;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MoviesRepositoryImpl implements MoviesRepository{
    private MoviesJpaRepository jpaRepository;
    private PeopleJpaRepository peopleJpaRepository;
    private GenresJpaRepository genresJpaRepository;

    public MoviesRepositoryImpl(MoviesJpaRepository jpaRepository, PeopleJpaRepository peopleJpaRepository, GenresJpaRepository genresJpaRepository) {
        this.jpaRepository = jpaRepository;
        this.peopleJpaRepository = peopleJpaRepository;
        this.genresJpaRepository = genresJpaRepository;
    }

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
}
