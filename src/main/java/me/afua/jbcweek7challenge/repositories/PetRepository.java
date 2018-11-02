package me.afua.jbcweek7challenge.repositories;

import me.afua.jbcweek7challenge.entities.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet,Long> {
}
