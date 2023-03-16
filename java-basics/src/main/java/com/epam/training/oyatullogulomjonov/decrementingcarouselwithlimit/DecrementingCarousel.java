package com.epam.training.oyatullogulomjonov.decrementingcarouselwithlimit;

class DecrementingCarousel {
    public static int[] numbers;
    public boolean isRunCalled = false;    
    private static int capacity;
    private int numberCount = 0;
    
    public DecrementingCarousel(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
            numbers = new int[capacity];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean addElement(int element){
        if (element > 0 && numberCount < capacity && !isRunCalled) {
            numbers[numberCount] = element;
            numberCount++;
            return true;
        }        
        return false;
    }

    public CarouselRun run(){
        CarouselRun carouselRun = null;
        if (!isRunCalled) {
            isRunCalled = true;
            carouselRun = new CarouselRun();
        }        
        return carouselRun;
    }
}
