package com.epam.training.oyatullogulomjonov.transposematrix;

import java.util.Arrays;

public class TransposeMatrix {
    public static int[][] transpose(int[][] matrix) {
        int[][] transposedMatrix = new int[matrix[0].length][matrix.length];        

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                transposedMatrix[column][row] = matrix[row][column];
            }
        }        
        return transposedMatrix;
    }

    public static void main(String[] args) {
        System.out.println("Test your code here!\n");
        int[][] matrix = {
                {1, 2},
                {7, -13} };

        int[][] result = transpose(matrix);
        System.out.println(Arrays.deepToString(result).replace("],", "]\n"));
    }

}
