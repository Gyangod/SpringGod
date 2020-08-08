package com.gyangod.model;

import com.gyangod.embeddedentity.UserEntity;

import java.util.Date;
import java.util.List;

public class Group {

    private String groupId;

    private String groupName;

    private String groupTitle;

    private List<UserEntity> userEntities;

    private String packageId;

    private Integer occurrencesSelected;

    private Date paymentCompleteDate;

    private Date nextEventDate;

    private String groupLastUpdatedBy;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public Integer getOccurrencesSelected() {
        return occurrencesSelected;
    }

    public void setOccurrencesSelected(Integer occurrencesSelected) {
        this.occurrencesSelected = occurrencesSelected;
    }

    public Date getPaymentCompleteDate() {
        return paymentCompleteDate;
    }

    public void setPaymentCompleteDate(Date paymentCompleteDate) {
        this.paymentCompleteDate = paymentCompleteDate;
    }

    public Date getNextEventDate() {
        return nextEventDate;
    }

    public void setNextEventDate(Date nextEventDate) {
        this.nextEventDate = nextEventDate;
    }

    public String getGroupLastUpdatedBy() {
        return groupLastUpdatedBy;
    }

    public void setGroupLastUpdatedBy(String groupLastUpdatedBy) {
        this.groupLastUpdatedBy = groupLastUpdatedBy;
    }
}
