package com.gyangod.embeddedentity;

import java.util.Date;

public class PackageGroup {

    public PackageGroup(Integer occurrences) {
        this.occurrences = occurrences;
    }

    private String createdByUserId;

    private Double totalCostPerUser;

    private Integer occurrences;

    private Integer totalOccurrences;

    private Double totalWorkingHours;
    /**
     * total Working Hours registered in the group
     */
    private Double payingWorkingHours;

    private Double divisor;

    /**
     * The first time when package starts
     */
    private Date packageNextEventDate;
    /**
     * The last time when package ends
     */
    private Date packageLastEventDate;

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Double getTotalCostPerUser() {
        return totalCostPerUser;
    }

    public void setTotalCostPerUser(Double totalCostPerUser) {
        this.totalCostPerUser = totalCostPerUser;
    }

    public Integer getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(Integer occurrences) {
        this.occurrences = occurrences;
    }

    public Integer getTotalOccurrences() {
        return totalOccurrences;
    }

    public void setTotalOccurrences(Integer totalOccurrences) {
        this.totalOccurrences = totalOccurrences;
    }

    public Double getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(Double totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public Double getPayingWorkingHours() {
        return payingWorkingHours;
    }

    public void setPayingWorkingHours(Double payingWorkingHours) {
        this.payingWorkingHours = payingWorkingHours;
    }

    public Double getDivisor() {
        return divisor;
    }

    public void setDivisor(Double divisor) {
        this.divisor = divisor;
    }

    public Date getPackageNextEventDate() {
        return packageNextEventDate;
    }

    public void setPackageNextEventDate(Date packageNextEventDate) {
        this.packageNextEventDate = packageNextEventDate;
    }

    public Date getPackageLastEventDate() {
        return packageLastEventDate;
    }

    public void setPackageLastEventDate(Date packageLastEventDate) {
        this.packageLastEventDate = packageLastEventDate;
    }
}
