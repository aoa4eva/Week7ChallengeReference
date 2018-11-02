package me.afua.jbcweek7challenge.controllers;

import me.afua.jbcweek7challenge.entities.Person;
import me.afua.jbcweek7challenge.repositories.PersonRepository;
import me.afua.jbcweek7challenge.entities.Pet;
import me.afua.jbcweek7challenge.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

@Controller
public class MainController {

    @Autowired
    PersonRepository people;

    @Autowired
    PetRepository pets;

    @RequestMapping("/")
    public String showPeopleAndPets(Model model)
    {
        if(people.count()<1)
        {
            Person person = new Person("Sarah Smith");
            Pet aPet = new Pet("Fluffy");
            person.addPet(aPet);
            people.save(person);

        }
        model.addAttribute("people",people.findAll());
        return "index";
    }

    @GetMapping("/addpet")
    public String showPersonID(HttpServletRequest request, Model model)
    {


        Pet aPet = new Pet();

        if(request.getParameter("personid")!=null)
        {
            long personId = new Long (request.getParameter("personid"));
            aPet.getOwners().add(people.findById(personId).get());
        }

        model.addAttribute("aPet",aPet);
        model.addAttribute("people",people.findAll());
        return "addpet";
    }

    @PostMapping("/addpet")
    public String savePet(@ModelAttribute("aPet") Pet aPet, Model model )
    {
        pets.save(aPet);
       HashSet<Person> owners = new HashSet<>();
        for(Person eachOwner:aPet.getOwners())
        {
            Person copyOfOwner = eachOwner;
            copyOfOwner.addPet(aPet);
            owners.add(copyOfOwner);

        }
        people.saveAll(owners);
        return "redirect:/";
    }

    @GetMapping("/addperson")
    public String addPerson(Model model)
    {
        model.addAttribute("anOwner",new Person());
        return "addperson";
    }

    @PostMapping("/addperson")
    public String savePerson(@ModelAttribute("anOwner") Person person)
    {

        people.save(person);
        return "redirect:/";
    }
}
