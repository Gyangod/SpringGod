package com.gyangod.service;

import com.gyangod.model.Address;
import com.gyangod.model.CustomerAddress;

public interface AddressService {

    Address saveAddress(Address address, String userName) throws Exception;

    CustomerAddress getAllAddressForUser(String userName) throws Exception;
}
