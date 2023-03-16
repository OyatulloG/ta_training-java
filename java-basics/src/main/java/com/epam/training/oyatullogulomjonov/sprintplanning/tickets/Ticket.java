package com.epam.training.oyatullogulomjonov.sprintplanning.tickets;

public class Ticket {
    public int id;
    public String name;
    public boolean isCompleted = false;    
    private int estimatedTime;

    public Ticket(int id, String name, int estimatedTime) {
        this.id = id;
        this.name = name;
        this.estimatedTime = estimatedTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void complete() {
        isCompleted = true;
    }

    public int getEstimate() {
        return estimatedTime;
    }
}
