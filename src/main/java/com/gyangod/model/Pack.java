package com.gyangod.model;

import com.gyangod.embeddedentity.PackageOccurrences;
import org.springframework.data.geo.Point;

import java.util.List;

public class Pack {

    private String packageId;

    private List<String> standards;

    private String name;

    private String description;

    private Customer createdBy;

    private Customer teacher;

    private Double costPerHour;

    private Double monthlyDiscount;

    private Integer occurrences;

    private Double weeklyCost;

    private Double totalWeekHours;

    private Double totalMonthHours;

    private Double monthlyCost;

    private Boolean addressLock;

    private Address address;

    private String addressPlaceName;

    private Point location;

    private List<PackageOccurrences> occurrencesList;

    private List<String> subjects;

    private List<String> topics;

    private Boolean visibility;

    private Boolean isActive;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Customer createdBy) {
        this.createdBy = createdBy;
    }

    public Customer getTeacher() {
        return teacher;
    }

    public void setTeacher(Customer teacher) {
        this.teacher = teacher;
    }

    public Double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(Double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public Double getMonthlyDiscount() {
        return monthlyDiscount;
    }

    public void setMonthlyDiscount(Double monthlyDiscount) {
        this.monthlyDiscount = monthlyDiscount;
    }

    public Integer getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(Integer occurrences) {
        this.occurrences = occurrences;
    }

    public Double getWeeklyCost() {
        return weeklyCost;
    }

    public void setWeeklyCost(Double weeklyCost) {
        this.weeklyCost = weeklyCost;
    }

    public Double getTotalWeekHours() {
        return totalWeekHours;
    }

    public void setTotalWeekHours(Double totalWeekHours) {
        this.totalWeekHours = totalWeekHours;
    }

    public Double getTotalMonthHours() {
        return totalMonthHours;
    }

    public void setTotalMonthHours(Double totalMonthHours) {
        this.totalMonthHours = totalMonthHours;
    }

    public Double getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(Double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public Boolean getAddressLock() {
        return addressLock;
    }

    public void setAddressLock(Boolean addressLock) {
        this.addressLock = addressLock;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAddressPlaceName() {
        return addressPlaceName;
    }

    public void setAddressPlaceName(String addressPlaceName) {
        this.addressPlaceName = addressPlaceName;
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

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
