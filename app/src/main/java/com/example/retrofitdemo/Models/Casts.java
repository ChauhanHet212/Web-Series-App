package com.example.retrofitdemo.Models;

import java.io.Serializable;

public class Casts implements Serializable {
    Person person;
    Character character;

    public Casts(Person person, Character character) {
        this.person = person;
        this.character = character;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
