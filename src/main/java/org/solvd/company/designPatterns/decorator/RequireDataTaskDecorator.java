package org.solvd.company.designPatterns.decorator;

public class RequireDataTaskDecorator extends TaskDecorator {

    public RequireDataTaskDecorator(Task decoratedTask) {
        super(decoratedTask);
    }

    @Override
    public void addFunctionality() {
        System.out.println("Adding data requirement functionality to the task.");
        decoratedTask.addFunctionality();

    }

    @Override
    public int getHours() {
        return decoratedTask.getHours() + 10;
    }
}
