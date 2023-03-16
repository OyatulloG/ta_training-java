package com.epam.training.oyatullogulomjonov.cycleswap;

import java.util.Arrays;

class CycleSwap {
    static void cycleSwap(int[] array) {
    	if (array.length > 0) {
    	    int[] arrayWithoutLastElement = Arrays.copyOfRange(array, 0, array.length-1);
    	    array[0] = array[array.length-1];
    	    System.arraycopy(arrayWithoutLastElement, 0, array, 1, arrayWithoutLastElement.length);	
	}    	
    }

    static void cycleSwap(int[] array, int shift) {
    	if (array.length > 0) {
    	    int[] arrayWithoutLastShiftElements = Arrays.copyOfRange(array, 0, array.length-shift);
    	    System.arraycopy(array, array.length-shift, array, 0, shift);
    	    System.arraycopy(arrayWithoutLastShiftElements, 0, array, shift, arrayWithoutLastShiftElements.length);
    	}
    }
}
