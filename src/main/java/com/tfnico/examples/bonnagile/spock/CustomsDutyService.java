package com.tfnico.examples.bonnagile.spock;

class CustomsDutyService {

    private final ParcelRepository repository;
    private final Notifier notifier;

    CustomsDutyService(ParcelRepository repository,
                       Notifier notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    int getAmountToPay(String parcelId) {
        Parcel parcel = repository.findById(parcelId);
        ParcelType type = parcel.getType();
        //if (parcel.getValue() > 490) return parcel.getValue() + 1;
        return type.calculateAmountToPay(parcel.getValue());
    }


    public Parcel findParcel(String parcelId) {
        return repository.findById(parcelId);
    }

    public String registerParcel(int value, ParcelType type, String recipientEmail) {
        Parcel parcel = new Parcel(value, type);
        notifier.notifyParcelArrived(recipientEmail);
        return repository.save(parcel);
    }
}