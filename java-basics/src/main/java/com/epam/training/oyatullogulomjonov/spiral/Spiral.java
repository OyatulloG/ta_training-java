package com.epam.training.oyatullogulomjonov.spiral;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        int[][] matrix = new int[rows][columns];        
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = columns - 1;
        int numberCount = 1;
        int direction = 1;
        
        while(top <= bottom && left <= right) {            
            if (direction == 1) { 
                //from left to right
                for (int column = left; column <= right; column++) {
                    matrix[top][column] = numberCount;
                    numberCount++;
                }
                top++;
                direction = 2;                
            } else if (direction == 2) { 
                //from up to down
                for (int row = top; row <= bottom; row++) {
                    matrix[row][right] = numberCount;
                    numberCount++;
                }
                right--;
                direction = 3;                
            } else if (direction == 3) { 
                //from right to left
                for (int column = right; column >= left; column--) {
                    matrix[bottom][column] = numberCount;
                    numberCount++;
                }
                bottom--;
                direction = 4;                
            } else { 
                //from down to up
                for (int row = bottom; row >= top; row--) {
                    matrix[row][left] = numberCount;
                    numberCount++;
                }
                left++;
                direction = 1;
            }            
        }
        return matrix;
    }
}
