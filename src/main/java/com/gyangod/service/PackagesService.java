package com.gyangod.service;

import com.gyangod.model.Pack;

import java.util.List;

public interface PackagesService {

    Pack testEndToEnd(Pack packages) throws Exception;
    List<Pack> getAllPackages() throws Exception;
}
