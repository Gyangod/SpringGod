package com.gyangod.service;

import com.gyangod.exception.domain.EmailNotFoundException;
import com.gyangod.exception.domain.UserNotFoundException;
import com.gyangod.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    Customer findByUserName(String userName) throws UserNotFoundException;

    Customer findByEmailAddress(String emailAddress) throws EmailNotFoundException;

    Customer registerUser(Customer customer) throws Exception;
}
