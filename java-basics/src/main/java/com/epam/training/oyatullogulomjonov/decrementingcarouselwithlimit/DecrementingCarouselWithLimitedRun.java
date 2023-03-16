package com.epam.training.oyatullogulomjonov.decrementingcarouselwithlimit;

public class DecrementingCarouselWithLimitedRun extends DecrementingCarousel {
    public static int nextMethodLimit;

    public DecrementingCarouselWithLimitedRun(final int capacity, final int nextMethodLimit) {
        super(capacity);
        this.nextMethodLimit = nextMethodLimit;
    }
    
    public CarouselRun run(){
        LimitedCarouselRun limitedCarouselRun = null;
        if (!isRunCalled) {
            isRunCalled = true;
            limitedCarouselRun = new LimitedCarouselRun();
        }        
        return limitedCarouselRun;
    }    
}
