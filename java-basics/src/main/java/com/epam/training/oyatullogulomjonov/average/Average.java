package com.epam.training.oyatullogulomjonov.average;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int numberCount = 0;
        
        while(true) {
            int number = Integer.parseInt(scanner.next());
            if (number == 0) {
                break;
            }
            sum += number;
            numberCount++;
        }
        System.out.println(sum / numberCount);
    }

}
