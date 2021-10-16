package com.gyangod.model;

import com.gyangod.embeddedentity.PackageOccurrences;
import org.springframework.data.geo.Point;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Pack {

    private String id;

    private List<String> standards;

    private String name;

    private String description;

    private Date courseEndDate;

    private Customer createdBy;

    private Customer teacher;

    private String teacherName;

    private Boolean anyoneCanAddBatch;

    private Boolean refundable;

    private Double costPerHour;

    private Double courseDiscount;

    private Double weeklyCost;

    private Double totalWeekHours;

    private Double totalMonthHours;

    private Double monthlyCost;

    private Double courseDuration;

    private Boolean fixedCourse;

    private Point location;

    private Map<String,List<PackageOccurrences>> mapOccurrences;

    private List<String> subjects;

    private Boolean visibility;

    private Boolean active;

    public String getPackageId() {
        return id;
    }

    public void setPackageId(String id) {
        this.id = id;
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

    public Customer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Customer createdBy) {
        this.createdBy = createdBy;
    }

    public Customer getTeacher() {
        return teacher;
    }

    public void setTeacher(Customer teacher) {
        this.teacher = teacher;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Boolean getAnyoneCanAddBatch() {
        return anyoneCanAddBatch;
    }

    public void setAnyoneCanAddBatch(Boolean anyoneCanAddBatch) {
        this.anyoneCanAddBatch = anyoneCanAddBatch;
    }

    public Boolean getRefundable() {
        return refundable;
    }

    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    public Double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(Double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public Double getCourseDiscount() {
        return courseDiscount;
    }

    public void setCourseDiscount(Double courseDiscount) {
        this.courseDiscount = courseDiscount;
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

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
