package me.afua.jbcweek7challenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {
    @GeneratedValue
    @Id
    private long id;


    private String fullName;

    @JsonIgnoreProperties("owners")
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Pet> pets;

    public Person() {
        pets = new HashSet<>();
    }

    public Person(String fullName) {
        this.fullName = fullName;
        pets = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addPet(Pet p)
    {
        this.pets.add(p);
    }
}
