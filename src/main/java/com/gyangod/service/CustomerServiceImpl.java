package com.gyangod.service;

import com.gyangod.enums.statemachine.UserStatusState;
import com.gyangod.exception.domain.EmailNotFoundException;
import com.gyangod.exception.domain.UserNotFoundException;
import com.gyangod.model.UserPrincipal;
import com.gyangod.utils.CustomerConversion;
import com.gyangod.entity.CustomerEntity;
import com.gyangod.model.Customer;
import com.gyangod.repository.CustomerRepository;
import com.gyangod.utils.JWTTokenProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.gyangod.enums.UserRole.ROLE_STUDENT;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerLoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserPrincipal principal;
        CustomerEntity customerEntity = customerRepository.findByUserName(userName);
        if(customerEntity == null){
            throw new UsernameNotFoundException("User not found with username" + userName);
        }else{
            validateLoginAttempt(customerEntity);
            customerEntity.setLastLoginDateDisplay(customerEntity.getLastLoginDate());
            customerEntity.setLastLoginDate(new Date());
            customerEntity = customerRepository.save(customerEntity);
            LOGGER.info("Found user by username" + userName);
            principal = new UserPrincipal(customerEntity);
        }
        return principal;
    }

    private void validateLoginAttempt(CustomerEntity customerEntity) {
        if(!customerEntity.getUserStatus().equals(UserStatusState.LOCKED)) {
            if(loginAttemptService.hasExceededMaxAttempts(customerEntity.getUserName())) {
                customerEntity.setUserStatus(UserStatusState.LOCKED);
            } else {
                customerEntity.setUserStatus(UserStatusState.ACTIVE);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(customerEntity.getUserName());
        }
    }

    @Override
    public Customer findByUserName(String userName) throws UserNotFoundException {
        CustomerEntity customerEntity = customerRepository.findByUserName(userName);
        if(customerEntity == null){
            throw new UserNotFoundException();
        }
        return CustomerConversion.getCustomer(customerEntity);
    }

    @Override
    public Customer findByEmailAddress(String emailAddress) throws EmailNotFoundException {
        CustomerEntity customerEntity = customerRepository.findByEmailAddress(emailAddress);
        if(customerEntity == null){
            throw new EmailNotFoundException();
        }
        return CustomerConversion.getCustomer(customerEntity);
    }

    @Override
    public Customer registerUser(Customer customer) throws Exception {
        CustomerEntity customerEntity = CustomerConversion.getCustomerEntity(customer);
        customerEntity.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerEntity.setRole(ROLE_STUDENT.name());
        customerEntity.setAuthorities(ROLE_STUDENT.getAuthorities());
        customerEntity.setJoinDate();
        return CustomerConversion.getCustomer(customerRepository.save(customerEntity));
    }

    @Override
    public Customer loginUser(Customer customer) throws Exception {
        authenticate(customer.getUserName(), customer.getPassword());
        UserPrincipal principal = (UserPrincipal) this.loadUserByUsername(customer.getUserName());
        customer.setJwtToken(jwtTokenProvider.generateJwtToken(principal));
        return customer;
    }

    private void authenticate(String userName, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
    }

}
