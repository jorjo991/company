package org.solvd.company.designPatterns.decorator;

public class RequireQATaskDecorator extends TaskDecorator {

    public RequireQATaskDecorator(Task task) {

        super(task);
    }

    @Override
    public void addFunctionality() {
        System.out.println("Adding QA requirements to the task.");
        decoratedTask.addFunctionality();
    }

    @Override
    public int getHours() {
        return decoratedTask.getHours() + 5;
    }
}
