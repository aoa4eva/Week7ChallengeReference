package me.afua.jbcweek7challenge.repositories;

import me.afua.jbcweek7challenge.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {
}
