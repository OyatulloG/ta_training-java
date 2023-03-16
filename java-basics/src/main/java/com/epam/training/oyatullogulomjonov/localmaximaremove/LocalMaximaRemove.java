package com.epam.training.oyatullogulomjonov.localmaximaremove;

import java.util.Arrays;

public class LocalMaximaRemove {
    public static void main(String[] args) {
        int[] array = new int[]{18, 1, 3, 6, 7, -5};
        System.out.println(Arrays.toString(removeLocalMaxima(array)));
    }

    public static int[] removeLocalMaxima(int[] array){
        int notLocalMaximaCount = 0;
        int[] notLocalMaximas = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            //first elem
            if (i == 0) {
                if (array[i] <= array[i+1]) {
                    notLocalMaximas[notLocalMaximaCount] = array[i];
                    notLocalMaximaCount++;
                }
                continue;
            }

            //last elem
            if (i == array.length-1) {
                if (array[i] <= array[i-1]) {
                    notLocalMaximas[notLocalMaximaCount] = array[i];
                    notLocalMaximaCount++;
                }
                continue;
            }

            //middle elem
            if (array[i] <= array[i-1] || array[i] <= array[i+1]){
                notLocalMaximas[notLocalMaximaCount] = array[i];
                notLocalMaximaCount++;
            }
        }
        return Arrays.copyOfRange(notLocalMaximas, 0, notLocalMaximaCount);
    }
}
