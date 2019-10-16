package com.gyangod.conversion;

import com.gyangod.entity.CustomerEntity;
import com.gyangod.model.Customer;

public class CustomerConversion {

    public CustomerEntity getCustomerEntity(Customer customer){
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

    public Customer getCustomer(CustomerEntity customer){
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
