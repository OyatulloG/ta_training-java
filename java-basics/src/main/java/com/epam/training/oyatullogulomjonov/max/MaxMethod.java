package com.epam.training.oyatullogulomjonov.max;

public class MaxMethod {
    public static int max(int[] values) {
        int max = values[0];
        for (int value : values) {
            if (max < value) {
                max = value;
            }
        }
        return max;
    }
}
