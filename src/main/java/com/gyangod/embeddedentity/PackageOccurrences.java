package com.gyangod.embeddedentity;

import com.gyangod.constants.DaysInWeek;

public class PackageOccurrences {

    private String fromTime;

    private String toTime;

    private Double timeDifference;

    private Boolean isActive = true;

    private Boolean repeatable = true;

    private String day;

    public PackageOccurrences(){}

    public PackageOccurrences(String fromTime, String toTime, Double timeDifference, Boolean isActive, Boolean repeatable, String day) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.timeDifference = timeDifference;
        this.isActive = isActive;
        this.repeatable = repeatable;
        this.day = day;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public Double getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(Double timeDifference) {
        this.timeDifference = timeDifference;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getRepeatable() {
        return repeatable;
    }

    public void setRepeatable(Boolean repeatable) {
        this.repeatable = repeatable;
    }

    public DaysInWeek getDay() {
        return DaysInWeek.valueOf(day);
    }

    public void setDay(DaysInWeek day) {
        this.day = day.name();
    }
}
