package com.tfnico.examples.bonnagile.spock

import spock.lang.Specification

class CustomsDutyTest extends Specification {

    private CustomsDutyService service
    private ParcelRepository repository
    Notifier notifier

    def setup() {
        repository = Mock(ParcelRepository)
        notifier = Mock(Notifier)
        service = new CustomsDutyService(repository, notifier)
    }

    def "should not look up wrong parcel"(){
        repository.findById("abcd") >> null
        service.registerParcel(300, ParcelType.GIFT, "")

        expect:
        service.findParcel("abcd") == null

    }

    def "calculate amount to pay for gift"() {
        setup:
        repository.findById("abcd") >> new Parcel(30, ParcelType.GIFT)

        expect:
        service.getAmountToPay("abcd") == 0
    }

    def "calculate amount to pay for commercial"() {
        setup:
        repository.findById("abc") >> new Parcel(30, ParcelType.COMMERCIAL)

        expect:
        service.getAmountToPay("abc") == 3
    }

    //@Unroll
    def "calculate amount to pay for all"(int val, ParcelType parcelType, int amountToPay){
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

    def "when parcel is registered, recipient should be notified"(){
        given:
        String recipient = "tfnico@gmail.com"

        when:
        service.registerParcel(200, ParcelType.GIFT, recipient)

        then:
        1 * notifier.notifyParcelArrived(recipient)

    }
}
