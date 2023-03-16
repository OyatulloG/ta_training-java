package com.epam.training.oyatullogulomjonov.meetstrangers;

import java.io.IOException;
import java.util.Scanner;

public class HelloStrangers {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int strangerCount = Integer.parseInt(scanner.nextLine());

        if (strangerCount > 0) {
            for (int i = 0; i < strangerCount; i++) {
                String strangerName = scanner.nextLine();
                System.out.println("Hello, " + strangerName);
            }
        } else if (strangerCount == 0) {
            System.out.println("Oh, it looks like there is no one here");
        } else {
            System.out.println("Seriously? Why so negative?");
        }
    }
}
