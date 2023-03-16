package com.epam.training.oyatullogulomjonov.taskcarousel;

public class CompleteByRequestTask implements Task {
    private boolean isComplete = false;
    private boolean isTaskFinished = false;

    public void complete() {
        isComplete = true;
    }
        
    @Override
    public void execute() {
        if(isComplete) {
            isTaskFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isTaskFinished;
    }
}
