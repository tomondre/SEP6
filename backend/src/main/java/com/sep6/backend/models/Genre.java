package com.sep6.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Data
@Builder
@ToString(exclude = "movies")
@EqualsAndHashCode(exclude = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Genre {
    @Id
    private int id;
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;
}
