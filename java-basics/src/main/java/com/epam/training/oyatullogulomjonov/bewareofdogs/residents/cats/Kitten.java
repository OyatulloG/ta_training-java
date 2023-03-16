package com.epam.training.oyatullogulomjonov.bewareofdogs.residents.cats;

public class Kitten extends Cat {
    public Kitten(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Kitten " + name;
    }
}
