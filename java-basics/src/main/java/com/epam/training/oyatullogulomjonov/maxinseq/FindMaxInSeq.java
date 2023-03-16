package com.epam.training.oyatullogulomjonov.maxinseq;

import java.util.Scanner;

public class FindMaxInSeq {
    public static int max() {
        Scanner scanner = new Scanner(System.in);
        int max = 0;
        boolean isFirstIteration = true;
        
        while(true) {
            int number = Integer.parseInt(scanner.next());
            if (number == 0) {
                break;
            }

            if (isFirstIteration) {
                max = number;
                isFirstIteration = false;
            }
            if (max < number) {
                max = number;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("Test your code here!\n");
        System.out.println(max());
    }
}
