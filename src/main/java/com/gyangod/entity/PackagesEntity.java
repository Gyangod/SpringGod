package com.gyangod.entity;

import com.gyangod.embeddedentity.PackageOccurrences;
import com.gyangod.enums.PackageState;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "PACKAGES")
public class PackagesEntity {

    @Id
    private String _id;

    private String standards;

    private String createdByUserId;

    private Double costPerUser;

    private Float discountRate;

    private Boolean visibility;

    private Integer occurrences;

    private Integer totalWorkingHours;

    private Integer totalWorkingDays;

    private Boolean addressLock;

    private List<PackageOccurrences> occurrencesList;

    private String packageStatus;

    public String getPackageId() {
        return _id;
    }

    public void setPackageId(String packageId) {
        this._id = packageId;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
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

    public Integer getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(Integer totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public Integer getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(Integer totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public Boolean getAddressLock() {
        return addressLock;
    }

    public void setAddressLock(Boolean addressLock) {
        this.addressLock = addressLock;
    }

    public List<PackageOccurrences> getOccurrencesList() {
        return occurrencesList;
    }

    public void setOccurrencesList(List<PackageOccurrences> occurrencesList) {
        this.occurrencesList = occurrencesList;
    }

    public PackageState getPackageStatus() {
        return PackageState.valueOf(packageStatus);
    }

    public void setPackageStatus(PackageState packageStatus) {
        this.packageStatus = packageStatus.name();
    }
}
