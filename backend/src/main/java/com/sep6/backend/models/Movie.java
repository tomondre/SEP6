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
    private List<Genre> genres;
    @ManyToMany
    private List<Person> people;
}
