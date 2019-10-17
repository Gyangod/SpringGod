package com.gyangod.model;

import com.gyangod.embeddedentity.PackageOccurrences;

import java.util.List;

public class Pack {

    private String packageId;

    private String standards;

    private List<PackageOccurrences> occurrencesList;

    private String packageStatus;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public List<PackageOccurrences> getOccurrencesList() {
        return occurrencesList;
    }

    public void setOccurrencesList(List<PackageOccurrences> occurrencesList) {
        this.occurrencesList = occurrencesList;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "packageId='" + packageId + '\'' +
                ", standards='" + standards + '\'' +
                ", occurrencesList=" + occurrencesList +
                ", packageStatus='" + packageStatus + '\'' +
                '}';
    }
}
