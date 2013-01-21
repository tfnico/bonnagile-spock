package com.tfnico.examples.bonnagile.spock;

public class Parcel {

    private int value;
    private ParcelType type;

    public int getValue() {
        return value;
    }

    public Parcel(int value, ParcelType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "value=" + value +
                '}';
    }

    public ParcelType getType() {
        return type;
    }
}
