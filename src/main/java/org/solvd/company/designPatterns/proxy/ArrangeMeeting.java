package org.solvd.company.designPatterns.proxy;

public class ArrangeMeeting implements Arrange {

    @Override
    public void meeting() {
        System.out.println("Meeting arranged successfully.");
    }

}
