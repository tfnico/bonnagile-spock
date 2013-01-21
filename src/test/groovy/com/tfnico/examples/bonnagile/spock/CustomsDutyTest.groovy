package com.tfnico.examples.bonnagile.spock

import spock.lang.Specification

class CustomsDutyTest extends Specification {

    private CustomsDutyService service
    private ParcelRepository repository

    def setup() {
        repository = Stub(ParcelRepository)
        service = new CustomsDutyService(repository)
    }

    def "look up parcel by id"(){
        setup:
        service.saveParcel("abcd", 0)

        expect:
        service.findParcel("abcd") != null
    }

    def "should not look up wrong parcel"(){
        repository.findById("abcd") >> null
        service.saveParcel("123ab", 0)

        expect:
        service.findParcel("abcd") == null

    }

    def "parcel should have same amountToPay as when saved"(){
        setup:
        repository.findById(_) >> new Parcel(500)
        when:
        service.saveParcel("abcd", 500)

        then:
        service.findParcel("abcd").amountToPay == 500
    }

    def "register parcel by value for gift"() {
        setup:
        String parcelId = service.registerParcel(30, ParcelType.GIFT)

        expect:
        service.findParcel(parcelId).amountToPay == 0
    }

    def "register parcel by value for commercial shipment"() {
        setup:
        repository.save(_) >> "abc"
        repository.findById("abc") >> new Parcel(3)
        String parcelId = service.registerParcel(30, ParcelType.COMMERCIAL)

        expect:
        service.findParcel(parcelId).amountToPay == 3
    }

}
