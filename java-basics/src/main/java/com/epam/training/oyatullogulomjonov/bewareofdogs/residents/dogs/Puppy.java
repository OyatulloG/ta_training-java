package com.epam.training.oyatullogulomjonov.bewareofdogs.residents.dogs;

public class Puppy extends Dog {
    public Puppy(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Puppy " + name;
    }
}
