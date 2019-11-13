package com.gyangod.entity;

import com.gyangod.embeddedentity.PackageGroup;
import com.gyangod.embeddedentity.UserEntity;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed(name = "groupNameIndex")
    private String groupName;

    private String groupTitle;
    /**
     * List of all the users involved in this group and their details
     */
    private List<UserEntity> userEntities;
    /**
     * this is totalAmount/Divisor
     */
    private Double paymentAmount;

    private String packageId;
    /**
     * A customised Details of the Package by the Group Admin.
     */
    private PackageGroup packageGroup;
    /**
     * By this date payment must be completed. Just 2 hours before.
     */
    private Date paymentCompleteDate;
    /**
     * At this time user will get notification. half an hour ago.
     */
    private Date notificationTime;
    /**
     * At this date old transaction detail will be removed.
     * Usually this is the end time of the first package occurrence
     */
    private Date statusRefreshDate;
    /**
     * This is the last date of the package when the group have created or after status refresh
     */
    private Date packageExpiryDate;
    /**
     * UserId of who last changed the group settings.
     */
    private String groupLastUpdatedByUserName;

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

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public PackageGroup getPackageGroup() {
        return packageGroup;
    }

    public void setPackageGroup(PackageGroup packageGroup) {
        this.packageGroup = packageGroup;
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

    public String getGroupLastUpdatedByUserName() {
        return groupLastUpdatedByUserName;
    }

    public void setGroupLastUpdatedByUserName(String groupLastUpdatedByUserName) {
        this.groupLastUpdatedByUserName = groupLastUpdatedByUserName;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }
}
