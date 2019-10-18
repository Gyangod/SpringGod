package com.gyangod.service;

import com.gyangod.utils.CustomerConversion;
import com.gyangod.entity.CustomerEntity;
import com.gyangod.model.Customer;
import com.gyangod.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer endToEndTest(Customer customer) throws Exception {
        try {
            CustomerEntity customerEntity = CustomerConversion.getCustomerEntity(customer);
            customerEntity  = customerRepository.save(customerEntity);
            return CustomerConversion.getCustomer(customerEntity);

        }catch (Exception e){
            //todo: Application Notification
            throw e;
        }
    }
}
