package org.solvd.company.designPatterns.decorator;

public class RequireSETaskDecorator extends TaskDecorator {

    public RequireSETaskDecorator(Task task) {
        super(task);
    }

    @Override
    public void addFunctionality() {
        System.out.println("Adding Software Engineer requirement to the task.");
        decoratedTask.addFunctionality();
    }

    @Override
    public int getHours() {
        return decoratedTask.getHours() + 8;
    }
}
