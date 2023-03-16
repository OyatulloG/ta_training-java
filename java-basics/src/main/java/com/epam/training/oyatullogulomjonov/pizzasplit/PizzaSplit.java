package com.epam.training.oyatullogulomjonov.pizzasplit;

import java.util.Scanner;

public class PizzaSplit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int peopleCount = Integer.parseInt(scanner.next());
        int piecesPerPizza = Integer.parseInt(scanner.next());
        int pizzaCount = 1;        
        while (pizzaCount * piecesPerPizza % peopleCount != 0) {
            pizzaCount++;
        }
        System.out.println(pizzaCount);
    }
}
