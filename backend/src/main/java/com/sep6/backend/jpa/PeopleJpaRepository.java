package com.sep6.backend.jpa;

import com.sep6.backend.models.Person;
import com.sep6.backend.models.PersonType;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface PeopleJpaRepository extends CrudRepository<Person, Integer> {
    List<Person> getAllByType(PersonType type);
    List<Person> findAllByTypeAndNameContainingIgnoreCase(PersonType type, String name);
    Optional<Person> findByTypeAndId(PersonType type, int id);
}
