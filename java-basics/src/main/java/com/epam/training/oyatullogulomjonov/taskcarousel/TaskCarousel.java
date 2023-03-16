package com.epam.training.oyatullogulomjonov.taskcarousel;

public class TaskCarousel {
    private int taskCapacity;
    private Task[] tasks;
    private int taskCount = 0;
    private int index = 0;
    
    public TaskCarousel(int taskCapacity) {
        this.taskCapacity = taskCapacity;
        tasks = new Task[taskCapacity];
    }

    public boolean addTask(Task task) {
        if (task != null && !task.isFinished() && !isFull()) {
            tasks[taskCount] = task;
            taskCount++;
            return true;
        }
        return false;
    }
    
    public boolean execute() {
        if (taskCount > 0) {
            tasks[index].execute();
            if (tasks[index].isFinished()) {
                removeTask(index);
                taskCount--;
                index = (index < taskCount) ? index : 0;
            } else {
                index = (index+1 < taskCount) ? ++index : 0;    
            }
            return true;
        }
        return false;
    }

    public boolean isFull() {
        return taskCount == taskCapacity;
    }

    public boolean isEmpty() {
         return !(taskCount > 0);
    }
    
    private void removeTask(int index) {
        System.arraycopy(tasks, index+1, tasks, index, taskCount-index-1);
    }    
}
