package com.tfnico.examples.bonnagile.spock

import spock.lang.Specification

class DataDrivenTest extends Specification {

    private CustomsDutyService service
    private ParcelRepository repository
    Notifier notifier

    def setup() {
        repository = Mock(ParcelRepository)
        notifier = Mock(Notifier)
        service = new CustomsDutyService(repository, notifier)
    }

    def "calculate amount to pay for all commercial parcels"() {
        setup:
        repository.findById("abc") >> new Parcel(value, type)

        expect:
        service.getAmountToPay("abc") == amountToPay

        where:
        value << [30, 50, 100]
        type = ParcelType.COMMERCIAL
        amountToPay << [3, 5, 10]
    }

    def "calculate amount to pay for all gifts"(int value, ParcelType type, int amountToPay) {
        setup:
        repository.findById("abcd") >> new Parcel(value, type)

        expect:
        service.getAmountToPay("abcd") == 0

        where:
        [value, type, amountToPay] << [[30, ParcelType.GIFT, 0]]
    }

    //@Unroll
    def "calculate amount to pay for all"(int val, ParcelType parcelType, int amountToPay) {
        setup:
        repository.findById("abc") >> new Parcel(val, parcelType)

        expect:
        service.getAmountToPay("abc") == amountToPay

        where:
        val | parcelType            || amountToPay
        100 | ParcelType.COMMERCIAL || 10
        80  | ParcelType.COMMERCIAL || 8
        100 | ParcelType.GIFT       || 0
        0   | ParcelType.GIFT       || 0
        -1  | ParcelType.GIFT       || 0
    }

    def "calculated amount to pay must never be greater than the value (sanity check)"(int value, ParcelType type) {
        setup:
        repository.findById("abc") >> new Parcel(value, type)

        expect:
        service.getAmountToPay("abc") <= value

        where:
        [value, type] << ParcelData.take(100)
    }

}
