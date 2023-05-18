package com.sep6.backend.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Movie> movies;
}
