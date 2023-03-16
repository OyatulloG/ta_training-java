package com.epam.training.oyatullogulomjonov.sprintplanning.tickets;

import java.util.Arrays;

public class UserStory extends Ticket {
    private UserStory[] dependencies;

    public UserStory(int id, String name, int estimatedTime, UserStory... dependsOn) {
        super(id, name, estimatedTime);
        dependencies = dependsOn;
    }

    @Override
    public void complete() {
        boolean isUncompletedDependency = false;
        for (UserStory dependency : dependencies) {
            if (!dependency.isCompleted()) {
                isUncompletedDependency = true;
                break;
            }
        }
        isCompleted = !isUncompletedDependency;
    }

    public UserStory[] getDependencies() {
        return Arrays.copyOf(dependencies, dependencies.length);
    }

    @Override
    public String toString() {
        return "[US " + id + "] " + name;
    }
}
