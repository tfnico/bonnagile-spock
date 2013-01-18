package com.tfnico.examples.bonnagile.spock;

public class Case {

    private int amount;

    public Case(int amountToPay) {
        this.amount = amountToPay;
    }

    public int getAmountToPay() {
        return amount;
    }

    @Override
    public String toString() {
        return "Case{" +
                "amount=" + amount +
                '}';
    }
}
