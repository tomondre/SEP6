package com.sep6.backend.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private PersonType type;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    private String placeOfBirth;
    private char gender;
    @Column( length = 100000 )
    private String biography;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deathDate;
    private String profileImg;
    @ManyToMany(mappedBy = "people")
    @JsonIgnore
    private List<Movie> movies;

    public Person() {
    }

    public Person(int id, String name, PersonType type, Date dateOfBirth, String placeOfBirth, char gender, String biography, Date deathDate, String profileImg, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.gender = gender;
        this.biography = biography;
        this.deathDate = deathDate;
        this.profileImg = profileImg;
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
