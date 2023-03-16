package com.epam.training.oyatullogulomjonov.quadraticequation;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        //ax^2 + bx + c = 0
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double coefficientA = scanner.nextDouble();
        double coefficientB = scanner.nextDouble();
        double coefficientC = scanner.nextDouble();
        double discriminant = coefficientB * coefficientB - 4 * coefficientA * coefficientC;

        if (discriminant > 0) {
            double firstRoot = (-coefficientB + Math.sqrt(discriminant)) / (2 * coefficientA);
            double secondRoot = (-coefficientB - Math.sqrt(discriminant)) / (2 * coefficientA);
            System.out.println(firstRoot + " " + secondRoot);
        } else if (discriminant == 0) {
            double root = -coefficientB / (2 * coefficientA);
            System.out.println(root);
        } else {
            System.out.println("no roots");
        }
    }
}
