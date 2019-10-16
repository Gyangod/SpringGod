package com.gyangod.service;

import com.gyangod.entity.CustomerEntity;
import com.gyangod.model.Customer;

public interface CustomerService {

    CustomerEntity endToEndTest(Customer customer) throws Exception;
}
