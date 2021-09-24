package com.gyangod.utils;

import com.gyangod.embeddedentity.PackageGroup;
import com.gyangod.embeddedentity.PackageOccurrences;
import com.gyangod.embeddedentity.UserEntity;
import com.gyangod.entity.CustomerEntity;
import com.gyangod.entity.GroupsEntity;
import com.gyangod.entity.PackagesEntity;
import com.gyangod.enums.statemachine.PackageState;
import com.gyangod.enums.UserRoles;
import com.gyangod.model.Group;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupConversion {

   /* public static GroupsEntity getGroupEntity(Group group){
        GroupsEntity entity = new GroupsEntity();
        if ((group.getGroupId() != null))   entity.set_id(group.getGroupId());
        if ((group.getGroupName() != null))   entity.setGroupName(group.getGroupName());
        if ((group.getGroupTitle() != null))   entity.setGroupTitle(group.getGroupTitle());
        if ((group.getUserEntities() != null))   entity.setUserEntities(group.getUserEntities());
        if ((group.getPackageId() != null))   entity.setPackageId(group.getPackageId());
        if ((group.getOccurrencesSelected() != null))   {
            entity.setPackageGroup(new PackageGroup(group.getOccurrencesSelected()));
        }
        if ((group.getPaymentCompleteDate() != null))   entity.setPaymentCompleteDate(group.getPaymentCompleteDate());
        return entity;
    }
    public static Group getGroup(GroupsEntity group){
        Group entity = new Group();
        if ((group.get_id() != null))   entity.setGroupId(group.get_id());
        if ((group.getGroupName() != null))   entity.setGroupName(group.getGroupName());
        if ((group.getGroupTitle() != null))   entity.setGroupTitle(group.getGroupTitle());
        if ((group.getUserEntities() != null))   entity.setUserEntities(group.getUserEntities());
        if ((group.getPackageId() != null))   entity.setPackageId(group.getPackageId());
        if ((group.getPackageGroup() != null) && (group.getPackageGroup().getOccurrences()!=null))   {
            entity.setOccurrencesSelected(group.getPackageGroup().getOccurrences());
        }
        if ((group.getPaymentCompleteDate() != null))   entity.setPaymentCompleteDate(group.getPaymentCompleteDate());
        return entity;
    }

    public static GroupsEntity fillPackageDetails(GroupsEntity groupsEntity, PackagesEntity packagesEntity){
        if(groupsEntity!=null && groupsEntity.getPackageGroup()==null) return null;
        groupsEntity.getPackageGroup().setCreatedByUserId(packagesEntity.getCreatedByUserId());
        groupsEntity.getPackageGroup().setTotalCostPerUser(packagesEntity.getCostPerUser());
        groupsEntity.getPackageGroup().setTotalOccurrences(packagesEntity.getOccurrences());
        groupsEntity.getPackageGroup().setTotalWorkingHours(packagesEntity.getTotalWorkingHours());
        groupsEntity = fillOccurrenceDetails(groupsEntity,packagesEntity.getOccurrencesList());
        groupsEntity.setPaymentAmount(packagesEntity.getCostPerUser()*groupsEntity.getPackageGroup().getDivisor());
        return groupsEntity;
    }

    public static GroupsEntity fillOccurrenceDetails(GroupsEntity groupsEntity, List<PackageOccurrences> packageOccurrences){
        if(groupsEntity!=null && groupsEntity.getPackageGroup() == null) return null;
        Date currentTime = new Date();
        Integer occurrence = 0;
        Boolean newFlag = true;
        double workingHours = 0.0;
        if(packageOccurrences!=null && packageOccurrences.size()>0){
            for(PackageOccurrences packageOccurrence : packageOccurrences){
                if(currentTime.before(packageOccurrence.getFromTime())){
                    if(newFlag) {
                        groupsEntity.getPackageGroup().setPackageNextEventDate(packageOccurrence.getFromTime());
                        groupsEntity.setStatusRefreshDate(packageOccurrence.getToTime());
                        newFlag = false;
                    }
                    occurrence++;
                    groupsEntity.getPackageGroup().setPackageLastEventDate(packageOccurrence.getFromTime());
                    workingHours += packageOccurrence.getWorkingHours();
                    groupsEntity.setPackageExpiryDate(packageOccurrence.getToTime());
                    if(occurrence.equals(groupsEntity.getPackageGroup().getOccurrences())) break;
                }
            }
            groupsEntity.getPackageGroup().setPayingWorkingHours(workingHours);
            groupsEntity.getPackageGroup().setDivisor(groupsEntity.getPackageGroup().getTotalWorkingHours()/workingHours);
        }
        return groupsEntity;
    }

    public static GroupsEntity fillUserDetails(GroupsEntity workingGroupsEntity, CustomerEntity customerEntity, PackageState packageState,Boolean newFlag) {
        if (packageState.equals(PackageState.STUDENT) || packageState.equals(PackageState.TEACHER)) {
            UserRoles role;
            if (newFlag) {
                workingGroupsEntity.setUserEntities(new ArrayList<>());
                if (packageState.equals(PackageState.TEACHER)) role = UserRoles.TEACHERADMIN;
                else role = UserRoles.STUDENTADMIN;
            } else {
                if(packageState.equals(PackageState.TEACHER)) role = UserRoles.STUDENT;
                else role = UserRoles.TEACHER;
            }
            UserEntity userEntity = new UserEntity();
            userEntity.setUserRole(role.getCode());
            //TODO: Work with frontEnd Team.
//            workingGroupsEntity.setUserEntities(updateUserDetails(workingGroupsEntity.getUserEntities(),userEntity));
            return workingGroupsEntity;
        }
        return null;
    }*/
/*
    public static List<UserEntity> updateUserDetails(List<UserEntity> userEntities,UserEntity newUserEntity){

        return userEntities;
    }*/

}
