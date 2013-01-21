package com.tfnico.examples.bonnagile.spock;


public enum ParcelType {
    GIFT {
        @Override
        public int calculateAmountToPay(int value) {
            return 0;
        }
    }, COMMERCIAL {
        @Override
        public int calculateAmountToPay(int value) {
            return value/10;
        }
    };

    abstract public int calculateAmountToPay(int value);
}