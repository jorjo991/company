package org.solvd.company.designPatterns.strategy;

public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.println("Client paid" + amount + " using Cash.");
    }
}
