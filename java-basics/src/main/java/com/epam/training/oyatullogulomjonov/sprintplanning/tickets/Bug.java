package com.epam.training.oyatullogulomjonov.sprintplanning.tickets;

public class Bug extends Ticket {
    private UserStory userStory;

    private Bug(int id, String name, int estimatedTime, UserStory userStory) {
        super(id, name, estimatedTime);
        this.userStory = userStory;
    }
    
    public static Bug createBug(int id, String name, int estimatedTime, UserStory userStory) {
        Bug bug = null;
        if (userStory != null && userStory.isCompleted()) {
            bug = new Bug(id, name, estimatedTime, userStory);
        }
        return bug;
    }

    @Override
    public String toString() {
        return "[Bug " + id + "] " + userStory.getName() + ": " + name;
    }
}
