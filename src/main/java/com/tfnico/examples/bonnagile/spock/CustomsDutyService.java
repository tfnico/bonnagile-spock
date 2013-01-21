package com.tfnico.examples.bonnagile.spock;

class CustomsDutyService {

    private final ParcelRepository repository;

    CustomsDutyService(ParcelRepository repository) {
        this.repository = repository;
    }

    public Parcel findParcel(String parcelId) {
        return repository.findById(parcelId);
    }

    public void saveParcel(String parcelId, int amountToPay) {
        Parcel parcel = new Parcel(amountToPay);
        this.repository.save(parcel);
    }

    public String registerParcel(int value, ParcelType type) {
        Parcel parcel = new Parcel(0);
        //todo: generate id
        return repository.save(parcel);
    }
}