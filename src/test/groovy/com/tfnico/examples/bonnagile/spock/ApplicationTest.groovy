package com.tfnico.examples.bonnagile.spock

import spock.lang.Specification
import spock.lang.Unroll

class ApplicationTest extends Specification {

    Application application
    ParcelRepository repository

    def setup() {
        repository = Mock(ParcelRepository)
        application = new Application(repository)
    }

    def "Register parcel at the zollamt"(){
        given:
        repository.findById(_) >> new Parcel(value: 200)

        when:
        String parcelId = application.registerParcel(200)

        then:
        application.findByParcelId(parcelId) == new Parcel(value: 200)
    }

    def "Should not retrieve the wrong parcel"(){

        setup:
        repository.findById(_) >> null

        when:
        String parcelId = application.registerParcel(200)

        then:
        application.findByParcelId("789") == null

    }

    def "calculate amount to pay for gift"() {
        setup:
        repository.findById("123") >> new Parcel(value: 500, type: ParcelType.GIFT)
        expect:
        application.getAmountToPay("123") == 0
    }

    def "calculate amount to pay for commercial"(){
        setup:
        repository.findById("123") >> new Parcel(value: 500, type: ParcelType.COMMERCIAL)

        expect:
        application.getAmountToPay("123") == 50
    }

   // @Unroll("parcel #type with value #value should cost #amountToPay")
    def "calculate amount to pay for parcels"(int value, ParcelType type, int amountToPay) {
        setup:
        repository.findById("123") >> new Parcel(value: value, type: type)

        expect:
        application.getAmountToPay("123") == amountToPay

        where:
        value   | type                  || amountToPay
        100     | ParcelType.GIFT       || 0
        500     | ParcelType.GIFT       || 0
        30      | ParcelType.COMMERCIAL || 3
        100     | ParcelType.COMMERCIAL || 10
    }

    def "register parcel should call repo some way"(){

        when:
        String parcelId = application.registerParcel(200)

        then:
        1 * repository.save({ Parcel parcel -> parcel.value == 200 })
    }

    def "test order of many parcels registered"(){
        when:
        application.registerParcel(100)

        then:
        1 * repository.save({ it.value == 100 })

        when:
        application.registerParcel(200)
        application.registerParcel(300)

        then:
        1 * repository.save({ it.value == 200 })
        and:
        0..5 * repository.save({ it.value == 300 })
    }

    def "when save throws exception it should bubble up"(){

        setup:
        repository.findById(_) >> { throw new RuntimeException("Bad argument: "+it[0] ) }

        when:
        application.findByParcelId("123")

        then:
        def e = thrown(RuntimeException)
        e.message.endsWith("123")
    }

    def "use mock call argument in return values"(){

        setup:
        def mockService = Mock(CustomsDutyService){
            registerParcel(_ as Integer, _ as ParcelType, _ as String) >>
                    {int value, ParcelType type, String recipient -> "$value - $type - $recipient"}
        }

        expect:
        mockService.registerParcel(200, ParcelType.COMMERCIAL, "tfnico") == "oijsr"
    }



}
