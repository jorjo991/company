package org.solvd.company.designPatterns.strategy;

public class ClientPay {

    private PaymentStrategy paymentStrategy;

    public ClientPay(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void clientPayment(int amount) {
        paymentStrategy.pay(amount);
    }
}
