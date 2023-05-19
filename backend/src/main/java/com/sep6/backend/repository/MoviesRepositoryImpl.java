package com.sep6.backend.repository;

import com.sep6.backend.projections.MovieProjection;
import com.sep6.backend.jpa.GenresJpaRepository;
import com.sep6.backend.jpa.MoviesJpaRepository;
import com.sep6.backend.models.Genre;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Person;
import com.sep6.backend.projections.MovieProjection;
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
    private ActorsRepository actorsRepository;
    private GenresRepository genresRepository;

    @Override
    public Movie createMovie(Movie movie)
    {
        List<Person> people = movie.getPeople();
        for (Person person : people) {
            Optional<Person> byId = actorsRepository.findById(person.getId());
            if (byId.isEmpty()) {
                actorsRepository.save(person);
            }
        }

        List<Genre> genres = movie.getGenres();
        for (Genre genre : genres) {
            Optional<Genre> byId = genresRepository.findById(genre.getId());
            if (byId.isEmpty()) {
                genresRepository.save(genre);
            }
        }

        return jpaRepository.save(movie);
    }

    @Override
    public List<MovieProjection> getMovies() {
        return jpaRepository.findAllByIdNotNull();
    }

    @Override
    public List<MovieProjection> getMoviesBySearch(String search) {
        return jpaRepository.findByTitleContainingIgnoreCase(search);
    }

    @Override
    public List<MovieProjection> getMoviesByGenreId(int genreId) {
        return jpaRepository.findByGenresId(genreId);
    }

    @Override
    public Optional<Movie> getMovieById(int id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<MovieProjection> getLatestMovies(int actualLimit) {
        Pageable pageable = PageRequest.of(0, actualLimit);
        return jpaRepository.findAllByOrderByReleaseDateDesc(pageable);
    }

    @Override
    public List<MovieProjection> getPaginatedMovies(int pageInt) {
        PageRequest page = PageRequest.of(pageInt, 12);
        return jpaRepository.findAllByIdNotNull(page).getContent();
    }

    @Override
    public Movie updateMovieRatingById(int movieId, double rating) {
        Movie movieById = getMovieById(movieId).get();
        movieById.setRating(rating);
        return jpaRepository.save(movieById);
    }

    @Override
    public Movie getMovieReferenceById(int movieId) {
        return jpaRepository.getReferenceById(movieId);
    }
}
