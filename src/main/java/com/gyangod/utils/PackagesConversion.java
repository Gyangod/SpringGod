package com.gyangod.utils;

import com.gyangod.entity.PackagesEntity;
import com.gyangod.enums.PackageState;
import com.gyangod.model.Pack;

public class PackagesConversion {

    public static PackagesEntity getPackagesEntity(Pack packages){
        PackagesEntity entity = new PackagesEntity();
        entity.setPackageId(packages.getPackageId());
        entity.setStandards(packages.getStandards());
        entity.setOccurrencesList(packages.getOccurrencesList());
        entity.setSubjects(packages.getSubjects());
        entity.setTopics(packages.getTopics());
        entity.setPackageStatus(PackageState.valueOf(packages.getPackageStatus()));
        return entity;
    }

    public static Pack getPackages(PackagesEntity packages){
        Pack entity = new Pack();
        entity.setPackageId(packages.getPackageId());
        entity.setStandards(packages.getStandards());
        entity.setOccurrencesList(packages.getOccurrencesList());
        entity.setPackageStatus(packages.getPackageStatus().name());
        entity.setSubjects(packages.getSubjects());
        entity.setTopics(packages.getTopics());
        return entity;
    }
}
