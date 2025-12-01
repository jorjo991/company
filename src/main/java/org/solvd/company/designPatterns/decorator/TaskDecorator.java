package org.solvd.company.designPatterns.decorator;

public abstract class TaskDecorator implements Task {

    protected Task decoratedTask;

    public TaskDecorator(Task decoratedTask) {
        this.decoratedTask = decoratedTask;
    }

}
