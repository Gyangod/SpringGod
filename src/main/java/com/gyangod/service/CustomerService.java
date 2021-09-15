package com.gyangod.service;

import com.gyangod.entity.CustomerEntity;
import com.gyangod.enums.statemachine.UserStatusEvents;
import com.gyangod.exception.domain.EmailNotFoundException;
import com.gyangod.exception.domain.UserNotFoundException;
import com.gyangod.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService extends UserDetailsService {

    CustomerEntity findByUserName(String userName) throws UserNotFoundException;

    CustomerEntity findByEmailAddress(String emailAddress) throws EmailNotFoundException;

    Customer registerUser(Customer customer, MultipartFile profileImage) throws Exception;

    Customer loginUser(Customer customer) throws Exception;

    Customer updateUser(String currentUserName,Customer customer,String oldToken, MultipartFile profileImage) throws Exception;
    //todo: make it by id
    boolean deleteUser(Customer customer) throws Exception;

    Customer changeUserStatus(String userName, UserStatusEvents event) throws Exception;

    Customer updatePassword(String oldPassword, Customer customer) throws Exception;

    Customer resetPassword(Customer customer) throws Exception;

    List<Customer> getAllUsers() throws Exception;

    byte[] getTempFiles(String userName) throws Exception;
}
