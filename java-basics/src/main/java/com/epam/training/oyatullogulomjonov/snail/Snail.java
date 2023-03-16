package com.epam.training.oyatullogulomjonov.snail;

import java.util.Scanner;

public class Snail {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int distanceUp = Integer.parseInt(scanner.nextLine());
        int distanceDown = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());

        if (distanceUp < height && distanceUp <= distanceDown) {
            System.out.println("Impossible");
        } else {
            int dayCount = 0;
            while(true) {
                height -= distanceUp;
                dayCount++;
                if (height <= 0) {
                    break;
                }
                height += distanceDown;
            }
            System.out.println(dayCount);
        }
    }
}
