package com.sep6.backend.jpa;

import com.sep6.backend.models.Person;
import com.sep6.backend.models.PersonType;
import com.sep6.backend.projections.PersonMoviesProjection;
import com.sep6.backend.projections.PersonProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface PeopleJpaRepository extends CrudRepository<Person, Integer> {
    List<PersonProjection> getAllByType(PersonType type);
    List<PersonProjection> findAllByTypeAndNameContainingIgnoreCase(PersonType type, String name);
    List<PersonProjection> findAllByNameContainingIgnoreCase(String search);
    Optional<PersonMoviesProjection> findByTypeAndId(PersonType type, int id);
    Page<PersonProjection> findAll(Pageable page);
 //    Workaround so that the projection method does not clash with findAll();
    List<PersonProjection> findAllByIdNotNull();
//    Workaround so that the projection method does not clash with findAll();
    PersonMoviesProjection findFirstById(int id);
}
