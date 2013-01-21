package com.tfnico.examples.bonnagile.spock

import spock.lang.Specification

class CustomsDutyTest extends Specification {

    private CustomsDutyService service
    private DutyRepository repository

    def setup() {
        repository = Stub(DutyRepository)
        service = new CustomsDutyService(repository)
    }

    def "look up case by id"(){
        setup:
        service.saveCase("abcd", 0)

        expect:
        service.findCase("abcd") != null
    }

    def "should not look up wrong case"(){
        repository.findById("abcd") >> null
        service.saveCase("123ab", 0)

        expect:
        service.findCase("abcd") == null

    }

    def "case should have same amountToPay as when saved"(){
        setup:
        repository.findById(_) >> new Case(500)
        when:
        service.saveCase("abcd", 500)

        then:
        service.findCase("abcd").amountToPay == 500
    }

    def "register case by value for present"() {
        setup:
        String caseId = service.registerCase(30, CaseType.PRESENT)

        expect:
        service.findCase(caseId).amountToPay == 0
    }

    def "register case by value for commercial shipment"() {
        setup:
        repository.save(_) >> "abc"
        repository.findById("abc") >> new Case(3)
        String caseId = service.registerCase(30, CaseType.COMMERCIAL)

        expect:
        service.findCase(caseId).amountToPay == 3
    }

}
