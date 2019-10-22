package com.gyangod.service;

import com.gyangod.model.Customer;

public interface CustomerService {

    Customer findByUserName(String userName);

    Customer endToEndTest(Customer customer) throws Exception;
}
