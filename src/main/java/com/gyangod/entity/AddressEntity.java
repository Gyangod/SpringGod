package com.gyangod.entity;

import com.gyangod.embeddedentity.AddressPlace;
import com.gyangod.embeddedentity.PackageOccurrences;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "ADDRESS")
public class AddressEntity {

    @Id
    private String _id;

    @Indexed(name = "customer_address")
    private String customerId;

    private String addressLine1;

    private String addressLine2;

    private String pinCode;

    private String city;

    private String state;

    private String country;

    private List<AddressPlace> places;

    @GeoSpatialIndexed(name = "address_location", type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;

    private Integer addressUsedCount;

    private List<PackageOccurrences> addressPackageOccurrences;

    private String addressStatus;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Integer getAddressUsedCount() {
        return addressUsedCount;
    }

    public void setAddressUsedCount(Integer addressUsedCount) {
        this.addressUsedCount = addressUsedCount;
    }

    public List<PackageOccurrences> getAddressPackageOccurrences() {
        return addressPackageOccurrences;
    }

    public void setAddressPackageOccurrences(List<PackageOccurrences> addressPackageOccurrences) {
        this.addressPackageOccurrences = addressPackageOccurrences;
    }

    public String getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(String addressStatus) {
        this.addressStatus = addressStatus;
    }
}
