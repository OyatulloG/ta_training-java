package com.epam.training.oyatullogulomjonov.sumofprevious;

import java.util.Arrays;

public class SumOfPrevious {
    public static void main(String[] args) {
        int[] array = new int[]{1, -1, 0, 4, 6, 10, 15, 25};
        System.out.println(Arrays.toString(getSumCheckArray(array)));
    }

    public static boolean[] getSumCheckArray(int[] array){
        boolean[] isSumOfTwoPreviousElementsArray = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || i == 1) {
                isSumOfTwoPreviousElementsArray[i] = false;
                continue;
            }
            if (array[i] == array[i-1] + array[i-2]) {
                isSumOfTwoPreviousElementsArray[i] = true;
            }
        }
        return isSumOfTwoPreviousElementsArray;
    }
}
