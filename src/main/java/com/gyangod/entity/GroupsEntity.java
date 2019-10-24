package com.gyangod.entity;

import com.gyangod.embeddedentity.PackageEntity;
import com.gyangod.embeddedentity.UserEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Document(collection = "GROUPS")
public class GroupsEntity {

    @Id
    private String _id;
    /**
     * Name of the group Can be changed by admin only but group Title by anyone.
     */
    private String groupName;

    private String groupTitle;
    /**
     * List of all the users involved in this group and their details
     */
    private List<UserEntity> userEntities;
    /**
     * this is totalAmount/Divisor
     */
    private Float paymentAmount;

    private String packageId;
    /**
     * A customised Details of the Package by the Group Admin.
     */
    private PackageEntity packageEntity;
    /**
     * By this date payment must be completed. Usually 4 hours before.
     */
    private Date paymentCompleteDate;
    /**
     * At this time user will get notification.
     */
    private Date notificationTime;
    /**
     * At this date old transaction detail will be removed.
     * Usually this is the start time of the second last package occurrence
     */
    private Date statusRefreshDate;
    /**
     * This is the last date of the package when the group have created or after status refresh
     */
    private Date packageExpiryDate;
    /**
     * UserId of who last changed the group settings.
     */
    private String groupLastUpdatedByUserId;

    private String groupStatus;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public PackageEntity getPackageEntity() {
        return packageEntity;
    }

    public void setPackageEntity(PackageEntity packageEntity) {
        this.packageEntity = packageEntity;
    }

    public Date getPaymentCompleteDate() {
        return paymentCompleteDate;
    }

    public void setPaymentCompleteDate(Date paymentCompleteDate) {
        this.paymentCompleteDate = paymentCompleteDate;
    }

    public Date getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Date notificationTime) {
        this.notificationTime = notificationTime;
    }

    public Date getStatusRefreshDate() {
        return statusRefreshDate;
    }

    public void setStatusRefreshDate(Date statusRefreshDate) {
        this.statusRefreshDate = statusRefreshDate;
    }

    public Date getPackageExpiryDate() {
        return packageExpiryDate;
    }

    public void setPackageExpiryDate(Date packageExpiryDate) {
        this.packageExpiryDate = packageExpiryDate;
    }

    public String getGroupLastUpdatedByUserId() {
        return groupLastUpdatedByUserId;
    }

    public void setGroupLastUpdatedByUserId(String groupLastUpdatedByUserId) {
        this.groupLastUpdatedByUserId = groupLastUpdatedByUserId;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }
}
