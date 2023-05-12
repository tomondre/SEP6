package com.sep6.backend.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    private PersonType type;
    private Date dateOfBirth;
    private Date placeOfBirth;
    private char gender;
    private String biography;
    private Date deathDate;
    private String profileImg;
    @ManyToMany
    private List<Movie> movies;
}