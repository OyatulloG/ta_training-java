package com.epam.training.oyatullogulomjonov.decrementingcarouselwithlimit;

public class CarouselRun {
    public int[] numbers = DecrementingCarousel.numbers;
    public int position = 0;

    public int next() {
        while (!isFinished()) {
            for (; position < numbers.length; position++) {
                if (numbers[position] > 0) {
                    int currentNumber = numbers[position];
                    numbers[position]--;
                    position++;
                    return currentNumber;
                }
            }    
            position = 0;
        }        
        return -1;
    }

    public boolean isFinished() {
        for (int number : numbers) {
            if (number > 0) {            
                return false;
            }
        }        
        return true;
    }
}
