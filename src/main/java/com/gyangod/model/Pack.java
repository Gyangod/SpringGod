package com.gyangod.model;

import com.gyangod.embeddedentity.PackageOccurrences;

import java.util.List;

public class Pack {

    private String packageId;

    private String standards;

    private List<PackageOccurrences> occurrencesList;

    private List<String> subjects;

    private List<String> topics;

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
