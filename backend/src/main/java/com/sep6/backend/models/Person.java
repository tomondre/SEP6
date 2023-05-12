package com.sep6.backend.models;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

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
}