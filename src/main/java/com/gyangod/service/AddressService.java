package com.gyangod.service;

import com.gyangod.model.Address;
import com.gyangod.model.CustomerAddress;

public interface AddressService {
    /**
     *
     * @param address of the user and their details
     * @param userName of the user who want to save their address
     * @return the complete and saved version of the address
     * @throws Exception Service Not Saving Exception
     */
    Address saveAddress(Address address, String userName) throws Exception;

    /**
     *
     * @param userName
     * @return
     * @throws Exception
     */
    CustomerAddress getAllAddressForUser(String userName) throws Exception;
}
