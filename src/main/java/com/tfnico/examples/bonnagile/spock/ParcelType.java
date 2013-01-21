package com.tfnico.examples.bonnagile.spock;

public enum ParcelType {
    GIFT {

        public int calculateAmountToPay(int value) {
            return 0   ;
        }
    },
    COMMERCIAL {

        public int calculateAmountToPay(int value) {
            return value / 10  ;
        }
    };

    public abstract int  calculateAmountToPay(int value);
}
