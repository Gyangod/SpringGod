package com.gyangod.service;

import com.gyangod.entity.PackagesEntity;
import com.gyangod.model.HttpResponse;
import com.gyangod.model.Pack;
import com.gyangod.model.PackageSearch;

import java.util.List;

public interface PackagesService {

    HttpResponse savePackage(Pack pack, String userName) throws Exception;
    HttpResponse teachPackage(Pack pack,String userName) throws Exception;
    List<Pack> getAllPackagesByUser(String token) throws Exception;
    List<PackagesEntity> getAllPackageNearMe(PackageSearch packageSearch) throws Exception;
    List<Pack> getAllPackages() throws Exception;
}
