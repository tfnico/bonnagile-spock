package com.tfnico.examples.bonnagile.spock

class ParcelData {

    static random() {
        int value = Math.round(Math.random() * 500)
        double randomType = Math.random()
        ParcelType type = randomType < 0.5 ? ParcelType.COMMERCIAL : ParcelType.GIFT
        return [value, type]
    }

    static Iterable<List> take(int n) {
        List<List> result = new ArrayList<List>(n)
        n.times { result << random()}
        return result
    }

}
