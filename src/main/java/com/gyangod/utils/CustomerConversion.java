package com.gyangod.utils;

import com.gyangod.entity.CustomerEntity;
import com.gyangod.model.Customer;

public class CustomerConversion {

    public static CustomerEntity getCustomerEntity(Customer customer){
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(customer.getCustomerId());
        entity.setUserName(customer.getUserName());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setLastName(customer.getLastName());
        entity.setEmailAddress(customer.getEmailAddress());
        entity.setCountryCode(customer.getCountryCode());
        entity.setContactNumber(customer.getContactNumber());
        return entity;
    }

    public static Customer getCustomer(CustomerEntity customer){
        Customer entity = new Customer();
        entity.setCustomerId(customer.getCustomerId());
        entity.setUserName(customer.getUserName());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setLastName(customer.getLastName());
        entity.setEmailAddress(customer.getEmailAddress());
        entity.setCountryCode(customer.getCountryCode());
        entity.setContactNumber(customer.getContactNumber());
        return entity;
    }
}
