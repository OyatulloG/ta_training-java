package com.epam.training.oyatullogulomjonov.bewareofdogs.residents.cats;

public class Cat {
    protected String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat " + name;
    }
}
