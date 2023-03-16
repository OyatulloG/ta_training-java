package com.epam.training.oyatullogulomjonov.graduallydecreasingcarousel;

public class GraduallyDecreasingCarouselRun extends CarouselRun {
    private int cycle = 1;
    
    public int next() {
        while (!isFinished()) {
            for (; position < numbers.length; position++) {
                if (numbers[position] > 0) {
                    int currentNumber = numbers[position];
                    numbers[position] -= cycle;
                    position++;
                    return currentNumber;
                }
            }    
            position = 0;
            cycle++;
        }                
        return -1;
    }
}
