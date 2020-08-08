package com.gyangod.entity;

import com.gyangod.embeddedentity.PackageOccurrences;
import com.gyangod.enums.PackageState;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "PACKAGES")
public class PackagesEntity {

    @Id
    private String _id;

    private List<String> standards;

    @Indexed(name = "customer_packages")
    private String createdByUserId;

    private Double costPerUser;

    private Float discountRate;

    private Boolean visibility;

    private Integer occurrences;

    private Double totalWorkingHours;

    private Boolean addressLock;

    private String addressId;

    @GeoSpatialIndexed(name = "package_location",type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;

    private List<PackageOccurrences> occurrencesList;

    private List<String> subjects;

    private List<String> topics;

    private String packageStatus;

    public String getPackageId() {
        return _id;
    }

    public void setPackageId(String packageId) {
        this._id = packageId;
    }

    public List<String> getStandards() {
        return standards;
    }

    public void setStandards(List<String> standards) {
        this.standards = standards;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Double getCostPerUser() {
        return costPerUser;
    }

    public void setCostPerUser(Double costPerUser) {
        this.costPerUser = costPerUser;
    }

    public Float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Float discountRate) {
        this.discountRate = discountRate;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Integer getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(Integer occurrences) {
        this.occurrences = occurrences;
    }

    public Double getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(Double totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public Boolean getAddressLock() {
        return addressLock;
    }

    public void setAddressLock(Boolean addressLock) {
        this.addressLock = addressLock;
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

    public PackageState getPackageStatus() {
        return PackageState.valueOf(packageStatus);
    }

    public void setPackageStatus(PackageState packageStatus) {
        this.packageStatus = packageStatus.name();
    }
}
