package com.tfnico.examples.bonnagile.spock

import spock.lang.Specification

class CustomsDutyTest extends Specification {

    private CustomsDutyService service
    private ParcelRepository repository

    def setup() {
        repository = Stub(ParcelRepository)
        service = new CustomsDutyService(repository)
    }

    def "should not look up wrong parcel"(){
        repository.findById("abcd") >> null
        service.registerParcel(300, ParcelType.GIFT)

        expect:
        service.findParcel("abcd") == null

    }

    def "calculate amount to pay for gift"() {
        setup:
        String parcelId = service.registerParcel(30, ParcelType.GIFT)

        expect:
        service.getAmountToPay(parcelId) == 0
    }

    def "calculate amount to pay for commercial"() {
        setup:
        repository.findById("abc") >> new Parcel(30, ParcelType.COMMERCIAL)

        expect:
        service.getAmountToPay("abc") == 3
    }
}
