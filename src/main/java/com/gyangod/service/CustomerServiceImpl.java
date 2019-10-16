package com.gyangod.service;

import com.gyangod.conversion.CustomerConversion;
import com.gyangod.entity.CustomerEntity;
import com.gyangod.model.Customer;
import com.gyangod.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private static CustomerConversion customerConversion = new CustomerConversion();

    @Override
    public CustomerEntity endToEndTest(Customer customer) throws Exception {
        try {
            CustomerEntity customerEntity = customerConversion.getCustomerEntity(customer);
            customerEntity  = customerRepository.save(customerEntity);
            System.out.println(customerEntity);
            return customerEntity;

        }catch (Exception e){
            //todo: Application Notification
            throw e;
        }
    }
}
