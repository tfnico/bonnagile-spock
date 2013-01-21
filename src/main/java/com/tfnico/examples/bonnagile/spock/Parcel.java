package com.tfnico.examples.bonnagile.spock;

public class Parcel {

    private int value;

    public int getValue() {
        return value;
    }

    public Parcel(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "value=" + value +
                '}';
    }
}
