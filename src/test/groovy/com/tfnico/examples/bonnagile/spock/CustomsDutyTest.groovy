package com.tfnico.examples.bonnagile.spock

import spock.lang.Specification

class CustomsDutyTest extends Specification {

    def "test some java code"(){
        setup:
        CustomsDutyService service = new CustomsDutyService()

        when:
        true

        then:
        true

    }
}
