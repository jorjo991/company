package org.solvd.company.designPatterns.decorator;

public class BasicTask implements Task {

    @Override
    public void addFunctionality() {
        System.out.println("Basic Task Functionality");

    }

    @Override
    public int getHours() {
        return 10;
    }
}
