package com.sep6.backend.jpa;

import com.sep6.backend.models.Person;
import com.sep6.backend.models.PersonType;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface PeopleJpaRepository extends CrudRepository<Person, Integer> {
    List<PersonProjection> getAllByType(PersonType type);
    List<PersonProjection> findAllByTypeAndNameContainingIgnoreCase(PersonType type, String name);
    Optional<PersonMoviesProjection> findByTypeAndId(PersonType type, int id);
}
