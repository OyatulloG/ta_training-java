package com.epam.training.oyatullogulomjonov.multiplymatrices;

import java.util.Arrays;

public class MultiplyMatrix {
    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int[][] productMatrix = new int[matrix1.length][matrix2[0].length];
        
        for (int row1 = 0; row1 < matrix1.length; row1++) {
            for (int column2 = 0; column2 < matrix2[0].length; column2++) {
                int sum = 0;
                for (int column1 = 0, row2 = 0; column1 < matrix1[0].length; column1++, row2++) {
                    sum += matrix1[row1][column1] * matrix2[row2][column2];
                }                
                productMatrix[row1][column2] = sum;
            }
        }
        return productMatrix;
    }

    public static void main(String[] args) {
        System.out.println("Test your code here!\n");
        int[][] a = {
                {0, 12345},
                {4509, 0},
                {3, 567} };

        int[][] b = {
                {653, 0, 25353},
                {0, 61, 6} };

        int[][] result = multiply(a, b);
        System.out.println(Arrays.deepToString(result).replace("],", "]\n"));
    }
}
