package com.gyangod.model;

import com.gyangod.embeddedentity.PackageOccurrences;
import org.springframework.data.geo.Point;

import java.util.List;

public class Pack {

    private String packageId;

    private List<String> standards;

    private List<PackageOccurrences> occurrencesList;

    private List<String> subjects;

    private List<String> topics;

    private Boolean visibility;

    private Boolean addressLock;

    private Double costPerUser;

    private String addressId;

    private Point location;

    private String packageStatus;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public List<String> getStandards() {
        return standards;
    }

    public void setStandards(List<String> standards) {
        this.standards = standards;
    }

    public List<PackageOccurrences> getOccurrencesList() {
        return occurrencesList;
    }

    public void setOccurrencesList(List<PackageOccurrences> occurrencesList) {
        this.occurrencesList = occurrencesList;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Boolean getAddressLock() {
        return addressLock;
    }

    public void setAddressLock(Boolean addressLock) {
        this.addressLock = addressLock;
    }

    public Double getCostPerUser() {
        return costPerUser;
    }

    public void setCostPerUser(Double costPerUser) {
        this.costPerUser = costPerUser;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }
}
