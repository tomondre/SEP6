package com.sep6.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(
            name = "genre_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(
            name = "person_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Person> people;
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "reviews_id")
    private List<Review> reviews;
    private double rating;
    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(name = "favourites", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
    @JsonIgnore
    private Set<Account> favouredBy;
}
