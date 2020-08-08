package com.gyangod.embeddedentity;

public class AddressPlace {

    private String roomName;

    private Integer minimumUserCount;

    private Integer maximumUserCount;

    private Integer roomUsedCount;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getMinimumUserCount() {
        return minimumUserCount;
    }

    public void setMinimumUserCount(Integer minimumUserCount) {
        this.minimumUserCount = minimumUserCount;
    }

    public Integer getMaximumUserCount() {
        return maximumUserCount;
    }

    public void setMaximumUserCount(Integer maximumUserCount) {
        this.maximumUserCount = maximumUserCount;
    }

    public Integer getRoomUsedCount() {
        return roomUsedCount;
    }

    public void setRoomUsedCount(Integer roomUsedCount) {
        this.roomUsedCount = roomUsedCount;
    }
}
