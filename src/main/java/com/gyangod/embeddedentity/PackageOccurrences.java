package com.gyangod.embeddedentity;

import java.util.Date;

public class PackageOccurrences {

    private Date fromTime;

    private Date toTime;

    private Integer sequence;

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "PackageOccurrences{" +
                "fromTime=" + fromTime +
                ", toTime=" + toTime +
                ", sequence=" + sequence +
                '}';
    }
}
