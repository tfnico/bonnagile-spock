package com.tfnico.examples.bonnagile.spock

/**
 * Created with IntelliJ IDEA.
 * User: tfnico
 * Date: 1/21/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ParcelRepository {

    def findById(String parcelId)
    def save(Parcel parcel)
}