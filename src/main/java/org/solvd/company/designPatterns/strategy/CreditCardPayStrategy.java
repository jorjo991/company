package org.solvd.company.designPatterns.strategy;

public class CreditCardPayStrategy implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.println("Client paid " + amount + " using Credit Card.");
    }
}
