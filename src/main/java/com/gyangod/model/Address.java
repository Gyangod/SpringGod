package com.gyangod.model;

import com.gyangod.embeddedentity.AddressPlace;
import com.gyangod.entity.CustomerEntity;

import javax.persistence.ManyToOne;
import java.util.List;

public class Address {

    private String addressId;

    @ManyToOne(targetEntity = CustomerEntity.class)
    private String customerId;

    private String addressLine1;

    private String addressLine2;

    private String pinCode;

    private String city;

    private String state;

    private String country;

    private List<AddressPlace> places;

    private Double latitude;

    private Double longitude;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<AddressPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<AddressPlace> places) {
        this.places = places;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
