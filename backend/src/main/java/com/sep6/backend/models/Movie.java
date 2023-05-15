package com.sep6.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Movie {
    @Id
    private int id;

    private String title;
    @Column( length = 100000 )
    private String description;

    private String posterUrl;

    private int runtime;
    private String language;
    private int boxOffice;
    private int budget;
    private String status;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date releaseDate;
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
    @OneToMany(mappedBy = "movie")
    @Column(name = "reviews_id")
    private List<Review> reviews;
    private double rating;

    public Movie() {
    }

    public Movie(int id, String title, String description, String posterUrl, int runtime, String language, int boxOffice, int budget, String status, Date releaseDate, List<Genre> genres, List<Person> people, List<Review> reviews, double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.runtime = runtime;
        this.language = language;
        this.boxOffice = boxOffice;
        this.budget = budget;
        this.status = status;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.people = people;
        this.reviews = reviews;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(int boxOffice) {
        this.boxOffice = boxOffice;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
