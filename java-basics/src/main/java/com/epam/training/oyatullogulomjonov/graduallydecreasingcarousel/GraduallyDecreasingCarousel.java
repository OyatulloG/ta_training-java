package com.epam.training.oyatullogulomjonov.graduallydecreasingcarousel;

public class GraduallyDecreasingCarousel extends DecrementingCarousel{
    public GraduallyDecreasingCarousel(final int capacity) {
        super(capacity);
    }
    
    public CarouselRun run(){
        GraduallyDecreasingCarouselRun graduallyDecreasingCarouselRun = null;
        if (!isRunCalled) {
            isRunCalled = true;
            graduallyDecreasingCarouselRun = new GraduallyDecreasingCarouselRun();
        }        
        return graduallyDecreasingCarouselRun;
    }    
}
