package com.gyangod.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
//import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "CUSTOMER")
public class CustomerEntity {

    @Id
    private String _id;

    @Indexed(name = "customer_username",unique = true,sparse = true)
//    @NotNull
    private String userName;

//    @NotNull
    private String firstName;

    private String lastName;

//    @NotNull
    private String emailAddress;

    private String countryCode;

//    @NotNull
    private String contactNumber;

    //todo: masking of password before saving to DB
    private String password;

    private Boolean isStudent;

    private Integer studentCount;

    private Boolean isTeacher;

    private Integer teacherCount;

    private Float teacherRatingAvg;

    private Boolean isFacilitator;

    private Integer facilitatorCount;

    private String bankDetailsId;

    private List<String> addresses;

    private String userStatus;

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

    public Boolean getStudent() {
        return isStudent;
    }

    public void setStudent(Boolean student) {
        isStudent = student;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Boolean getTeacher() {
        return isTeacher;
    }

    public void setTeacher(Boolean teacher) {
        isTeacher = teacher;
    }

    public Integer getTeacherCount() {
        return teacherCount;
    }

    public void setTeacherCount(Integer teacherCount) {
        this.teacherCount = teacherCount;
    }

    public Float getTeacherRatingAvg() {
        return teacherRatingAvg;
    }

    public void setTeacherRatingAvg(Float teacherRatingAvg) {
        this.teacherRatingAvg = teacherRatingAvg;
    }

    public Boolean getFacilitator() {
        return isFacilitator;
    }

    public void setFacilitator(Boolean facilitator) {
        isFacilitator = facilitator;
    }

    public Integer getFacilitatorCount() {
        return facilitatorCount;
    }

    public void setFacilitatorCount(Integer facilitatorCount) {
        this.facilitatorCount = facilitatorCount;
    }

    public String getBankDetailsId() {
        return bankDetailsId;
    }

    public void setBankDetailsId(String bankDetailsId) {
        this.bankDetailsId = bankDetailsId;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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
