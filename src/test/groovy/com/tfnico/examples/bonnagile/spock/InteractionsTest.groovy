package com.tfnico.examples.bonnagile.spock

import spock.lang.FailsWith
import spock.lang.Ignore
import spock.lang.IgnoreIf
import spock.lang.Specification

class InteractionsTest extends Specification {

    private CustomsDutyService service
    private ParcelRepository repository
    Notifier notifier

    def setup() {
        repository = Mock(ParcelRepository)
        notifier = Mock(Notifier)
        service = new CustomsDutyService(repository, notifier)
    }

    def "register many parcels, verifying the order of when they are called"(){
        when:
        service.registerParcel(200, ParcelType.GIFT, "tfnico+1@gmail.com")
        service.registerParcel(200, ParcelType.GIFT, "tfnico+2@gmail.com")


        then:
        1 * notifier.notifyParcelArrived("tfnico+2@gmail.com")
        and:
        1 * notifier.notifyParcelArrived("tfnico+1@gmail.com")


        when:
        service.registerParcel(200, ParcelType.GIFT, "tfnico+3@gmail.com")

        then:
        with(notifier){
            1 * notifyParcelArrived({ it.contains("3")})
        }
    }

    @FailsWith(RuntimeException)
    def "can't figure out this bug"(){
        repository.save(_) >> { throw new RuntimeException("Unknown error")}
    }

    @Ignore("Disable this test just for fun")
    def "mock and handle exceptions"(){
        setup:
        repository.findById(_ as String) >>  { throw new RuntimeException("Bad argument:" +it[0] )}

        when:
        service.findParcel("123")

        then:
        def e = thrown(RuntimeException)
        e.message.endsWith("123")
    }

    @IgnoreIf({ System.getenv("USER").equals("daniel")})
    def "ignore this test maybe?"(){
        expect:
        System.getenv("USER") == "tfnico"
        //do some funky stuff that doesn't work on windows
    }

    def "show off mocking a sequence of return values"(){
        given:
        repository.findById(_) >>> [new Parcel(123,ParcelType.COMMERCIAL),
                new Parcel(321, ParcelType.COMMERCIAL),
                new Parcel(567,ParcelType.GIFT)
        ]

        expect:
        service.findParcel("a").value == 123
        service.findParcel("a").value == 321
        service.findParcel("a").value == 567
    }

    def "use mock call arguments in return values"(){
        given:
        def mockService = Mock(CustomsDutyService){
            registerParcel(_ as Integer, _ as ParcelType, _ as String) >> {int value, ParcelType parcelType, String recipient -> "$value - $parcelType - $recipient" }
        }

        expect:
        mockService.registerParcel(1, ParcelType.GIFT, "tfnico") == "1 - GIFT - tfnico"
    }

    def "stubs and spies"(){
        ParcelRepository repo = Stub(ParcelRepository) // will just return dummy objects, as parcelType the first member of the enum
        CustomsDutyService service = Spy(CustomsDutyService, constructorArgs: [repo, notifier]) //allows us to spy on methods

        when:
        service.getAmountToPay("123")

        then:
        1 * service.lookUpAllowedParcelTypes()
    }



}
