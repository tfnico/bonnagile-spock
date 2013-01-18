package com.tfnico.examples.bonnagile.spock

import spock.lang.Specification

class SomethingSpecTest extends Specification {

    def "do the foo"(){
        given:
        def foo

        when:
        foo = "foo"
        foo+= "bar"

        then:
        foo == "foobar"

    }

    def "test some java code"(){
        setup:
        App app = Mock(App)
        CustomsDutyService service = new CustomsDutyService()
        service.setApp(app)

        when:
        service.doTheFoo();

        then:
        1 * app.didIt()

    }
}
