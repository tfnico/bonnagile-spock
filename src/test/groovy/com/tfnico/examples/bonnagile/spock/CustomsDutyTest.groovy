package com.tfnico.examples.bonnagile.spock

import spock.lang.Specification

class CustomsDutyTest extends Specification {

    private CustomsDutyService service

    def "look up case by id"(){
        setup:
        service = new CustomsDutyService()

        this.service = service
        expect:
        this.service.findCase("abcd") != null
    }

    def "case should have same amountToPay as when saved"(){

        setup:
        service = new CustomsDutyService()
        service.saveCase("abcd", 500)

        expect:
        service.findCase("abcd").amountToPay == 500
    }

}
