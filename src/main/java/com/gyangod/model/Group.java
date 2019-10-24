package com.gyangod.model;

import com.gyangod.embeddedentity.UserEntity;

import java.util.List;

public class Group {

    private String groupId;

    private String groupName;

    private String groupTitle;

    private List<UserEntity> userEntities;

    private Float paymentAmount;

    private Integer occurrences;

}
