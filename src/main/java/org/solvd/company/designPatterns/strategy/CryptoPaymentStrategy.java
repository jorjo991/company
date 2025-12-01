package org.solvd.company.designPatterns.strategy;

public class CryptoPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Cryptocurrency.");
    }
}
