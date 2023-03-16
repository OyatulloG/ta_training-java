package com.epam.training.oyatullogulomjonov.halvingcarousel;

public class HalvingCarousel extends DecrementingCarousel {
    public HalvingCarousel(final int capacity) {
        super(capacity);
    }

    public CarouselRun run(){
        HalvingCarouselRun halvingCarouselRun = null;
        if (!isRunCalled) {
            isRunCalled = true;
            halvingCarouselRun = new HalvingCarouselRun();
        }        
        return halvingCarouselRun;
    }
}
