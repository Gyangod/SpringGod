package com.gyangod.entity;

import com.gyangod.embeddedentity.PackageOccurrences;
import com.gyangod.enums.statemachine.PackageState;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "PACKAGES")
public class PackagesEntity {

    @Id
    private String _id;

    private List<String> standards;

    private String name;

    private String description;

    private Date courseEndDate;

    @OneToOne(targetEntity = CustomerEntity.class)
    private String createdByUserId;

    @OneToOne(targetEntity = CustomerEntity.class)
    private String teacherId;

    private String teacherName;

    @NotNull
    private Boolean addOtherMembers = true;

    @NotNull //imp
    private Double costPerHour = 0.0;

    private Double monthlyDiscount;

    @NotNull
    private Double weeklyCost = 0.0;

    @NotNull //imp
    private Double totalWeekHours = 0.0;

    @NotNull
    private Double totalMonthHours = 0.0;

    @NotNull
    private Double monthlyCost = 0.0;

    private Double courseDuration;

    @NotNull
    private Boolean fixedCourse = false;

    @GeoSpatialIndexed(name = "package_location",type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;

    private Map<String,List<PackageOccurrences>> mapOccurrences;

    @Indexed(sparse = true)
    private List<String> subjects;

    @Indexed(sparse = true)
    private List<String> topics;

    @Indexed(sparse = true)
    private String packageStatus;

    public String getPackageId() {
        return _id;
    }

    public void setPackageId(String packageId) {
        this._id = packageId;
    }

    public List<String> getStandards() {
        return standards;
    }

    public void setStandards(List<String> standards) {
        this.standards = standards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Boolean getAddOtherMembers() {
        return addOtherMembers;
    }

    public void setAddOtherMembers(Boolean addOtherMembers) {
        this.addOtherMembers = addOtherMembers;
    }

    public Double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(Double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public Double getMonthlyDiscount() {
        return monthlyDiscount;
    }

    public void setMonthlyDiscount(Double monthlyDiscount) {
        this.monthlyDiscount = monthlyDiscount;
    }

    public Double getWeeklyCost() {
        return weeklyCost;
    }

    public void setWeeklyCost(Double weeklyCost) {
        this.weeklyCost = weeklyCost;
    }

    public Double getTotalWeekHours() {
        return totalWeekHours;
    }

    public void setTotalWeekHours(Double totalWeekHours) {
        this.totalWeekHours = totalWeekHours;
    }

    public Double getTotalMonthHours() {
        return totalMonthHours;
    }

    public void setTotalMonthHours(Double totalMonthHours) {
        this.totalMonthHours = totalMonthHours;
    }

    public Double getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(Double monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public Double getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(Double courseDuration) {
        this.courseDuration = courseDuration;
    }

    public Boolean getFixedCourse() {
        return fixedCourse;
    }

    public void setFixedCourse(Boolean fixedCourse) {
        this.fixedCourse = fixedCourse;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Map<String, List<PackageOccurrences>> getMapOccurrences() {
        return mapOccurrences;
    }

    public void setMapOccurrences(Map<String, List<PackageOccurrences>> mapOccurrences) {
        this.mapOccurrences = mapOccurrences;
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

    public PackageState getPackageStatus() {
        return PackageState.valueOf(packageStatus);
    }

    public void setPackageStatus(PackageState packageStatus) {
        this.packageStatus = packageStatus.name();
    }
}
