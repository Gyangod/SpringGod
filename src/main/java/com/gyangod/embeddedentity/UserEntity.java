package com.gyangod.embeddedentity;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class UserEntity {

    private String userId;

    private String userName;

    private String contactNumber;

    private Integer userRole;

    private Boolean askedForAdminRole;

    private Boolean addressAppliedFlag;

    private String addressId;

    private AddressPlace addressPlace;

    private String pinCode;

    private GeoJsonPoint geoJsonPoint;

    private Integer votesReceived;

    private Boolean hasVoted;

    private String votedUserId;

    private Float discountPercent;

    private String userCouponId;

    private String userCouponName;

    private String transactionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Boolean getAskedForAdminRole() {
        return askedForAdminRole;
    }

    public void setAskedForAdminRole(Boolean askedForAdminRole) {
        this.askedForAdminRole = askedForAdminRole;
    }

    public Boolean getAddressAppliedFlag() {
        return addressAppliedFlag;
    }

    public void setAddressAppliedFlag(Boolean addressAppliedFlag) {
        this.addressAppliedFlag = addressAppliedFlag;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public AddressPlace getAddressPlace() {
        return addressPlace;
    }

    public void setAddressPlace(AddressPlace addressPlace) {
        this.addressPlace = addressPlace;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public GeoJsonPoint getGeoJsonPoint() {
        return geoJsonPoint;
    }

    public void setGeoJsonPoint(GeoJsonPoint geoJsonPoint) {
        this.geoJsonPoint = geoJsonPoint;
    }

    public Integer getVotesReceived() {
        return votesReceived;
    }

    public void setVotesReceived(Integer votesReceived) {
        this.votesReceived = votesReceived;
    }

    public Boolean getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(Boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public String getVotedUserId() {
        return votedUserId;
    }

    public void setVotedUserId(String votedUserId) {
        this.votedUserId = votedUserId;
    }

    public Float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Float discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(String userCouponId) {
        this.userCouponId = userCouponId;
    }

    public String getUserCouponName() {
        return userCouponName;
    }

    public void setUserCouponName(String userCouponName) {
        this.userCouponName = userCouponName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
