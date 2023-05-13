package com.sep6.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Genre {
    @Id
    private int id;
    private String name;
    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;
}
