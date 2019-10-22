package com.gyangod.utils;

import com.gyangod.entity.AddressEntity;
import com.gyangod.entity.CustomerEntity;
import com.gyangod.model.Address;
import com.gyangod.model.CustomerAddress;
import org.bson.types.ObjectId;

public class AddressConversion {

    public AddressEntity getAddressEntity(Address entity) throws Exception{
        AddressEntity returnEntity =  new AddressEntity();
        returnEntity.set_id(entity.getAddressId());
        try{
            returnEntity.setCustomerId(new ObjectId(entity.getCustomerId()));
        }catch (Exception e){
            //todo: Throw Application Exception for illegal ObjectId
            throw e;
        }
        returnEntity.setAddressLine1(entity.getAddressLine1());
        returnEntity.setAddressLine2(entity.getAddressLine2());
        returnEntity.setPinCode(entity.getPinCode());
        returnEntity.setCity(entity.getCity());
        returnEntity.setState(entity.getState());
        returnEntity.setCountry(entity.getCountry());
        returnEntity.setPlaces(entity.getPlaces());
        returnEntity.setLatitude(entity.getLatitude());
        returnEntity.setLongitude(entity.getLongitude());
        return returnEntity;
    }

    public Address getAddress(AddressEntity entity){
        Address returnEntity = new Address();
        returnEntity.setAddressId(entity.get_id());
        returnEntity.setCustomerId(entity.getCustomerId().toHexString());
        returnEntity.setAddressLine1(entity.getAddressLine1());
        returnEntity.setAddressLine2(entity.getAddressLine2());
        returnEntity.setPinCode(entity.getPinCode());
        returnEntity.setCity(entity.getCity());
        returnEntity.setState(entity.getState());
        returnEntity.setCountry(entity.getCountry());
        returnEntity.setPlaces(entity.getPlaces());
        returnEntity.setLatitude(entity.getLatitude());
        returnEntity.setLongitude(entity.getLongitude());
        return returnEntity;
    }

    public CustomerAddress setInitialCustomer(CustomerEntity entity){
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCustomerId(entity.getCustomerId());
        customerAddress.setUserName(entity.getUserName());
        customerAddress.setFirstName(entity.getFirstName());
        customerAddress.setLastName(entity.getLastName());
        return customerAddress;
    }

}
