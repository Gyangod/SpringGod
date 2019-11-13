package com.gyangod.service;

import com.gyangod.model.Group;

public interface GroupService {

    /**
     * This function save or update a user in the group
     * @param group basic details of the group or existing group
     * @param userName the user who is willing to enter to the group
     * @return the structured and informed group after saving and processing it.
     * @throws Exception saving or fetching error or bad details
     */
    Group saveGroup(Group group, String userName) throws Exception;
}