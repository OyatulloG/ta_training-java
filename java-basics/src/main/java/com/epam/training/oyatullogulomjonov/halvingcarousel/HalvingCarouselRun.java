package com.epam.training.oyatullogulomjonov.halvingcarousel;

public class HalvingCarouselRun extends CarouselRun {
    public int next() {
        while (!isFinished()) {
            for (; position < numbers.length; position++) {
                if (numbers[position] > 0) {
                    int currentNumber = numbers[position];
                    numbers[position] /= 2;
                    position++;
                    return currentNumber;
                }
            }    
            position = 0;
        }        
        return -1;
    }
}
