package com.tfnico.examples.bonnagile.spock

class Application {

    private final ParcelRepository repository

    Application(ParcelRepository repository) {
        this.repository = repository
    }

    def registerParcel(int value) {
      repository.save(new Parcel(value: value))
    }

    def findByParcelId(String parcelId)      {
        return repository.findById(parcelId)
    }

    def getAmountToPay(String parcelId) {
        def parcel = repository.findById(parcelId)
        return parcel.type.calculateAmountToPay(parcel.value)
    }
}
