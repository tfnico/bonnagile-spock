package com.tfnico.examples.bonnagile.spock;

public interface ParcelRepository {
    Parcel findById(String parcelId);
    String save(Parcel parcel);
}
