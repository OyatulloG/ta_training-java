package com.epam.training.oyatullogulomjonov.bewareofdogs.house;

import com.epam.training.oyatullogulomjonov.bewareofdogs.residents.cats.Cat;
import com.epam.training.oyatullogulomjonov.bewareofdogs.residents.cats.Kitten;
import com.epam.training.oyatullogulomjonov.bewareofdogs.residents.dogs.Dog;
import com.epam.training.oyatullogulomjonov.bewareofdogs.residents.dogs.Puppy;

public class Main {
    public static void main(String[] args) {
        Dog rex = new Dog("Rax");
        Puppy randy = new Puppy("Randy");
        Cat barbos = new Cat("Barbos");
        Kitten murzik = new Kitten("Murzik");

        House<Dog> dogHouse = new House();
        dogHouse.enter(rex);
        dogHouse.enter(randy);
        System.out.println(dogHouse);

        House<Cat> catHouse = new House();
        catHouse.enter(barbos);
        catHouse.enter(murzik);
        System.out.println(catHouse);
    }
}
