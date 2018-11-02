package me.afua.jbcweek7challenge.controllers;

import me.afua.jbcweek7challenge.entities.Person;
import me.afua.jbcweek7challenge.repositories.PersonRepository;
import me.afua.jbcweek7challenge.entities.Pet;
import me.afua.jbcweek7challenge.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/json")
public class JSONController {

    @Autowired
    PersonRepository people;

    @Autowired
    PetRepository pets;

    @RequestMapping("/showpets")
    public Iterable <Pet> showPets()
    {
        return pets.findAll();
    }

    @RequestMapping("/showowners")
    public Iterable <Person> showOwners()
    {
        return people.findAll();
    }
}
