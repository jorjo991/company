package org.solvd.company.designPatterns.decorator;

public class Main {
    public static void main(String[] args) {

        Task task = new BasicTask();
        System.out.println(task.getHours());

        task = new RequireDataTaskDecorator(task);

        System.out.println(task.getHours());
        task.addFunctionality();

        task = new RequireQATaskDecorator(task);

        System.out.println(task.getHours());

    }
}
