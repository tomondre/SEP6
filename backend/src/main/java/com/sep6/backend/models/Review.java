package com.sep6.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Range(min=0, max=10)
    private double rating;
    @Column(length = 100000)
    private String comment;
    private LocalDateTime createdOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    @Column(name = "movie_id", insertable=false, updatable=false)
    private int movieId;
//    TODO
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_id", referencedColumnName = "id")
//    private Account account;
//    @Column(name = "account_id", insertable=false, updatable=false)
//    private int accountId;

    public Review() {
    }

    public Review(int id, double rating, String comment, LocalDateTime createdOn, Movie movie, int movieId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.createdOn = createdOn;
        this.movie = movie;
        this.movieId = movieId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
