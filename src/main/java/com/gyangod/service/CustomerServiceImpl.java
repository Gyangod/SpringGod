package com.gyangod.service;

import com.gyangod.model.UserPrincipal;
import com.gyangod.utils.CustomerConversion;
import com.gyangod.entity.CustomerEntity;
import com.gyangod.model.Customer;
import com.gyangod.repository.CustomerRepository;
import com.gyangod.validation.CustomerValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserPrincipal principal;
        CustomerEntity customerEntity = customerRepository.findByUserName(userName);
        if(customerEntity == null){
            throw new UsernameNotFoundException("User not found with username" + userName);
        }else{
            customerEntity.setLastLoginDateDisplay(customerEntity.getLastLoginDate());
            customerEntity.setLastLoginDate(new Date());
            customerEntity = customerRepository.save(customerEntity);
            LOGGER.info("Found user by username" + userName);
            principal = new UserPrincipal(customerEntity);
        }
        return principal;
    }

    @Override
    public Customer findByUserName(String userName) throws UsernameNotFoundException {
        CustomerEntity customerEntity = customerRepository.findByUserName(userName);
        if(customerEntity == null){
            throw new UsernameNotFoundException("User not found with username" + userName);
        }
        return CustomerConversion.getCustomer(customerEntity);
    }

    @Override
    public Customer saveUser(Customer customer) throws Exception {
        CustomerValidation.RequestValidator(customer);
        CustomerEntity customerEntity = CustomerConversion.getCustomerEntity(customer);
        return CustomerConversion.getCustomer(customerRepository.save(customerEntity));
    }

    @Override
    public Customer endToEndTest(Customer customer) throws Exception {
        try {
            CustomerEntity customerEntity = CustomerConversion.getCustomerEntity(customer);
            customerEntity  = customerRepository.save(customerEntity);
            return CustomerConversion.getCustomer(customerEntity);

        }catch (Exception e){
            LOGGER.error("Test",e);
            throw e;
        }
    }

}
