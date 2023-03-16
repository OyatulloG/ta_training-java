package com.epam.training.oyatullogulomjonov.decrementingcarouselwithlimit;

public class LimitedCarouselRun extends CarouselRun {
    private int nextMethodLimit = DecrementingCarouselWithLimitedRun.nextMethodLimit;
	
    public int next() {
        while (nextMethodLimit > 0 && isFinished() == false) {
            for (; position < numbers.length; position++) {
                if (numbers[position] > 0) {
                    int currentNumber = numbers[position];
                    numbers[position]--;
                    position++;
                    nextMethodLimit--;
                    return currentNumber;
                }
            }    
            position = 0;
        }        
        return -1;
    }

    public boolean isFinished() {
        if (nextMethodLimit > 0) {
            for (int number : numbers) {
            	if (number > 0)
		    return false;   
            }
        }        
        return true;
    }
}
