package com.gyangod.utils;

import com.gyangod.entity.PackagesEntity;
import com.gyangod.model.Pack;

public class PackagesConversion {

    public PackagesEntity getPackagesEntity(Pack packages){
        PackagesEntity entity = new PackagesEntity();
        entity.setPackageId(packages.getPackageId());
        entity.setStandards(packages.getStandards());
        //todo: convert Date to String
        entity.setOccurrencesList(packages.getOccurrencesList());
        entity.setPackageStatus(packages.getPackageStatus());
        return entity;
    }

    public Pack getPackages(PackagesEntity packages){
        Pack entity = new Pack();
        entity.setPackageId(packages.getPackageId());
        entity.setStandards(packages.getStandards());
        entity.setOccurrencesList(packages.getOccurrencesList());
        entity.setPackageStatus(packages.getPackageStatus());
        return entity;
    }
}
