package com.tfnico.examples.bonnagile.spock;

class CustomsDutyService {

    private final ParcelRepository repository;

    CustomsDutyService(ParcelRepository repository) {
        this.repository = repository;
    }

    int getAmountToPay(String parcelId) {
        Parcel parcel = repository.findById(parcelId);
        ParcelType type = parcel.getType();
        return type.calculateAmountToPay(parcel.getValue());
    }


    public Parcel findParcel(String parcelId) {
        return repository.findById(parcelId);
    }

    public String registerParcel(int value, ParcelType type) {
        Parcel parcel = new Parcel(value, type);
        //todo: generate id
        return repository.save(parcel);
    }
}