package com.gyangod.entity;

import com.gyangod.enums.statemachine.UserStatusState;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Document(collection = "CUSTOMER")
public class CustomerEntity {

    public CustomerEntity() {
        this.userStatus = UserStatusState.ACTIVE.name();
    }

    @Id
    private String _id;

    @Indexed(name = "customer_username",unique = true,sparse = true)
    @NotNull(message = "User Name cannot be null")
    private String userName;

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    private String lastName;

    @Indexed(name = "customer_email",unique = true)
    @NotNull(message = "Email Id cannot be null")
    @Email(message = "The format of the email is not correct")
    private String emailAddress;

    private String countryCode;

//    @NotNull
    private String contactNumber;

    private String password;

    private String role;

    private String[] authorities;

    private Date joinDate;

    private Date lastLoginDate;

    private Date lastLoginDateDisplay;

    private List<String> addresses;

    private String userStatus;

    //todo: save image in cloud and save the string in db

    public String getCustomerId() {
        return _id;
    }

    public void setCustomerId(String customerId) {
        this._id = customerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String roles) {
        this.role = roles;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate() {
        this.joinDate = new Date();
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }

    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public UserStatusState getUserStatus() {
        return UserStatusState.valueOf(userStatus);
    }

    public void setUserStatus(UserStatusState userStatus) {
        this.userStatus = userStatus.name();
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "customerId='" + _id + '\'' +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
