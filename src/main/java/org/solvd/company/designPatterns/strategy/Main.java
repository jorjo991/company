package org.solvd.company.designPatterns.strategy;

public class Main {

    public static void main(String[] args) {

        PaymentStrategy paymentStrategy = new CashPaymentStrategy();
        ClientPay clientPay = new ClientPay(paymentStrategy);
        clientPay.clientPayment(100);

        paymentStrategy = new CryptoPaymentStrategy();
        clientPay = new ClientPay(paymentStrategy);
        clientPay.clientPayment(100);

    }

}
