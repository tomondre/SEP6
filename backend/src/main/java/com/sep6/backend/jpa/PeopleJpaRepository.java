package com.sep6.backend.jpa;

import com.sep6.backend.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PeopleJpaRepository extends CrudRepository<Person, Integer> {
}
