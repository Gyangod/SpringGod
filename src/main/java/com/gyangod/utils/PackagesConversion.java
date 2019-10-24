package com.gyangod.utils;

import com.gyangod.entity.PackagesEntity;
import com.gyangod.enums.PackageState;
import com.gyangod.model.Pack;

public class PackagesConversion {

    public static PackagesEntity getPackagesEntity(Pack packages){
        PackagesEntity entity = new PackagesEntity();
        entity.setPackageId(packages.getPackageId());
        entity.setStandards(packages.getStandards());
        entity.setCostPerUser(packages.getCostPerUser());
        entity.setVisibility(packages.getVisibility());
        entity.setAddressLock(packages.getAddressLock());
        entity.setAddressId(packages.getAddressId());
        entity.setLocation(packages.getLocation());
        entity.setOccurrencesList(packages.getOccurrencesList());
        entity.setSubjects(packages.getSubjects());
        entity.setTopics(packages.getTopics());
        if(packages.getPackageStatus()!=null) entity.setPackageStatus(PackageState.valueOf(packages.getPackageStatus()));
        return entity;
    }

    public static Pack getPackages(PackagesEntity packages){
        Pack entity = new Pack();
        entity.setPackageId(packages.getPackageId());
        entity.setStandards(packages.getStandards());
        entity.setCostPerUser(packages.getCostPerUser());
        entity.setVisibility(packages.getVisibility());
        entity.setAddressLock(packages.getAddressLock());
        entity.setAddressId(packages.getAddressId());
        entity.setLocation(packages.getLocation());
        entity.setOccurrencesList(packages.getOccurrencesList());
        if(packages.getPackageStatus().name()!= null) entity.setPackageStatus(packages.getPackageStatus().name());
        entity.setSubjects(packages.getSubjects());
        entity.setTopics(packages.getTopics());
        return entity;
    }
}
