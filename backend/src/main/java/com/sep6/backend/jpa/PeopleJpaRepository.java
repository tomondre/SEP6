package com.sep6.backend.jpa;

import com.sep6.backend.models.Person;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PeopleJpaRepository extends CrudRepository<Person, Integer> {
    List<Person> getAllByType(String type);
    List<Person> findAllByTypeAndName(String type, String name);
    Person findByTypeAndId(String type, int id);
}
