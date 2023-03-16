package com.epam.training.oyatullogulomjonov.electronicwatch;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();
        
        //time format is h:mm:ss
        //1 day = 86400 seconds
        seconds %= 86400;
        int hour = seconds / 3600;
        seconds %= 3600;
        int minuteTens = seconds / 600;
        seconds %= 600;
        int minuteOnes = seconds / 60;
        seconds %= 60;
        int secondTens = seconds / 10;
        seconds %= 10;
        int secondOnes = seconds;
        
        System.out.println(hour + ":" + minuteTens + minuteOnes + ":" + secondTens + secondOnes);
    }
}
