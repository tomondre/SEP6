package com.sep6.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    @Column(name = "movie_id", insertable=false, updatable=false)
    private int movieId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Account account;
    @Column(name = "account_id", insertable=false, updatable=false)
    private int accountId;
}
