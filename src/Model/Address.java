package Model;

import MyEnum.AddressRefType;

public class Address {
    int addressID;
    String addressGUID;

    String street;
    String city;
    String state;
    String postalCode;
    String country;

    int refID;
    AddressRefType addressRefType;


    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddressGUID() {
        return addressGUID;
    }

    public void setAddressGUID(String addressGUID) {
        this.addressGUID = addressGUID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRefID() {
        return refID;
    }

    public void setRefID(int refID) {
        this.refID = refID;
    }

    public AddressRefType getAddressRefType() {
        return addressRefType;
    }

    public void setAddressRefType(AddressRefType addressRefType) {
        this.addressRefType = addressRefType;
    }
}
