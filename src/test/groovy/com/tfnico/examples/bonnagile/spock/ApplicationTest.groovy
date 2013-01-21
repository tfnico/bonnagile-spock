package com.tfnico.examples.bonnagile.spock

import spock.lang.Specification

class ApplicationTest extends Specification {

    def "Register parcel at the zollamt"(){
        given:
        Application application = new Application()

        when:
        String parcelId = application.registerParcel(200)

        then:
        application.findByParcelId(parcelId) != null


    }
}
