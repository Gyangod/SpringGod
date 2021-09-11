package com.gyangod.service;

import com.gyangod.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomerService extends UserDetailsService {

    Customer findByUserName(String userName) throws UsernameNotFoundException;

    Customer saveUser(Customer customer) throws Exception;
    @Deprecated
    Customer endToEndTest(Customer customer) throws Exception;
}
