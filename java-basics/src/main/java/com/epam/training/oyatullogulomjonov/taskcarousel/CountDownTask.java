package com.epam.training.oyatullogulomjonov.taskcarousel;

public class CountDownTask implements Task {
    private int countDown;
    private boolean isTaskFinished;

    public CountDownTask(int countDown) {
        this.countDown = (countDown >= 0) ? countDown : 0;
        isTaskFinished = (countDown > 0) ? false : true;
    }

    public int getValue() {
        return countDown;
    }

    @Override
    public void execute() {
        if (countDown > 0) {
            countDown--;
        } 
        
        if (countDown == 0) {
            isTaskFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isTaskFinished;
    }
}
