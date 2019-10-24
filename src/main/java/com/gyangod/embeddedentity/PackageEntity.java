package com.gyangod.embeddedentity;

import java.util.Date;

public class PackageEntity {

    private String createdByUserId;

    private Double totalCostPerUser;

    private Integer occurrences;

    private Integer totalOccurrences;

    private Float totalWorkingHours;
    /**
     * total Working Hours registered in the
     */
    private Float payingWorkingHours;

    private Float divisor;

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

    public Float getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(Float totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public Float getPayingWorkingHours() {
        return payingWorkingHours;
    }

    public void setPayingWorkingHours(Float payingWorkingHours) {
        this.payingWorkingHours = payingWorkingHours;
    }

    public Float getDivisor() {
        return divisor;
    }

    public void setDivisor(Float divisor) {
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
