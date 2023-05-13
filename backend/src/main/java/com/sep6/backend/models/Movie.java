package com.sep6.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Movie {
    @Id
    private int id;

    private String description;

    private String posterUrl;

    private int runtime;
    private String language;
    private int boxOffice;
    @ManyToMany
    @JoinTable(
            name = "genre_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;
    @ManyToMany
    @JoinTable(
            name = "person_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> people;
}
