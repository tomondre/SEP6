package com.sep6.backend.jpa;

import com.sep6.backend.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresJpaRepository extends JpaRepository<Genre, Integer> {
}
