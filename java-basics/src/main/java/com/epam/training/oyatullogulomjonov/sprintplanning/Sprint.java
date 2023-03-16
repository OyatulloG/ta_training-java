package com.epam.training.oyatullogulomjonov.sprintplanning;

import com.epam.training.oyatullogulomjonov.sprintplanning.tickets.Ticket;
import com.epam.training.oyatullogulomjonov.sprintplanning.tickets.UserStory;
import com.epam.training.oyatullogulomjonov.sprintplanning.tickets.Bug;
import java.util.Arrays;

public class Sprint {
    private int timeCapacity;
    private int ticketLimit;
    private Ticket[] tickets;
    private int ticketCount = 0;
    
    public Sprint(int timeCapacity, int ticketLimit) {
        this.timeCapacity = timeCapacity;
        this.ticketLimit = ticketLimit;
        tickets = new Ticket[ticketLimit];
    }

    public boolean addUserStory(UserStory userStory) {
        if (ticketCount < ticketLimit && userStory != null && !userStory.isCompleted()) {
            //uncompleted dependencies of userStory should already be accepted to the Sprint 
            //before userStory can be added to the Sprint
            for (UserStory dependency : userStory.getDependencies()) {
                if (!dependency.isCompleted() && !isAdded(dependency)) {
                    return false;
                }
            }
            
            if (getTotalEstimate() + userStory.getEstimate() <= timeCapacity) {
                tickets[ticketCount] = userStory;
                ticketCount++;
                return true;
            }
        }
        return false;
    }

    public boolean addBug(Bug bugReport) {
        if (ticketCount < ticketLimit && bugReport != null && !bugReport.isCompleted()) {
            if (getTotalEstimate() + bugReport.getEstimate() <= timeCapacity) {
                tickets[ticketCount] = bugReport;
                ticketCount++;
                return true;
            }
        }
        return false;
    }

    public Ticket[] getTickets() {
	return Arrays.copyOf(tickets, ticketCount);
    }

    public int getTotalEstimate() {
        int totalEstimatedTime = 0;
        for (int i = 0; i < ticketCount; i++) {
            totalEstimatedTime += tickets[i].getEstimate();
        }
        return totalEstimatedTime;
    }
    
    private boolean isAdded(Ticket ticket) {
        for (int i = 0; i < ticketCount; i++) {
            if (tickets[i] == ticket) {
                return true;
            }
        }
        return false;
    }    
}
