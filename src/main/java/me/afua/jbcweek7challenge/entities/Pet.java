package me.afua.jbcweek7challenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pet {
    @GeneratedValue
    @Id
    private long id;

    private String petName;

    @JsonIgnoreProperties("pets")
    @ManyToMany(mappedBy="pets")
    private Set<Person> owners;


    public Pet() {
        owners = new HashSet<>();
    }

    public Pet(String petName) {
        this.petName = petName;
        owners = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Person> getOwners() {
        return owners;
    }

    public void setOwners(Set<Person> owners) {
        this.owners = owners;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}
