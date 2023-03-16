package com.epam.training.oyatullogulomjonov.godutch;

import java.util.Scanner;

public class GoDutch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalBill = Integer.parseInt(scanner.nextLine());
        int friendCount = Integer.parseInt(scanner.nextLine());

        if (totalBill < 0) {
            System.out.println("Bill total amount cannot be negative");
        } else if (friendCount <= 0) {
            System.out.println("Number of friends cannot be negative or zero");
        } else {
            int totalBillWithTips = totalBill + totalBill / 10;
            int paymentPerPerson = totalBillWithTips / friendCount;
            System.out.println(paymentPerPerson);
        }
    }
}
