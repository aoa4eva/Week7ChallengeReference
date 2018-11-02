package me.afua.jbcweek7challenge.configuration;

import me.afua.jbcweek7challenge.entities.Person;
import me.afua.jbcweek7challenge.entities.Pet;
import me.afua.jbcweek7challenge.repositories.PersonRepository;
import me.afua.jbcweek7challenge.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    PersonRepository people;

    @Autowired
    PetRepository pets;

    @Override
    public void run(String... args) throws Exception {
        Person firstPerson = new Person("Sarah Smith");
        Person secondPerson = new Person("James Cameron");

        Pet firstPet = new Pet("Fluffy");
        firstPerson.getPets().add(firstPet);
        people.save(firstPerson);

        Pet secondPet = new Pet("Rover");
        secondPerson.getPets().add(secondPet);
        people.save(secondPerson);

        Pet thirdPet = new Pet("Chance");

        thirdPet.getOwners().add(firstPerson);
        thirdPet.getOwners().add(secondPerson);
        firstPerson.addPet(thirdPet);
        secondPerson.addPet(thirdPet);
        people.save(firstPerson);
        people.save(secondPerson);





    }
}
