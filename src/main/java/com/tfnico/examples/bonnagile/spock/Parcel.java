package com.tfnico.examples.bonnagile.spock;

public class Parcel {

    private int amount;

    public Parcel(int amountToPay) {
        this.amount = amountToPay;
    }

    public int getAmountToPay() {
        return amount;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "amount=" + amount +
                '}';
    }
}
