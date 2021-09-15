package com.gyangod.service;

import com.gyangod.enums.UserRole;
import com.gyangod.enums.statemachine.UserStatusEvents;
import com.gyangod.enums.statemachine.UserStatusState;
import com.gyangod.exception.domain.EmailNotFoundException;
import com.gyangod.exception.domain.UserNotFoundException;
import com.gyangod.model.UserPrincipal;
import com.gyangod.utils.CustomerConversion;
import com.gyangod.entity.CustomerEntity;
import com.gyangod.model.Customer;
import com.gyangod.repository.CustomerRepository;
import com.gyangod.utils.JWTTokenProvider;
import com.gyangod.validation.CustomerValidation;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private EmailService emailService;

    @Autowired
    @Qualifier("user")
    private StateMachineFactory<UserStatusState, UserStatusEvents> stateMachineFactory;

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
    public CustomerEntity findByUserName(String userName) throws UserNotFoundException {
        CustomerEntity customerEntity = customerRepository.findByUserName(userName);
        if(customerEntity == null){
            throw new UserNotFoundException();
        }
        return customerEntity;
    }

    @Override
    public CustomerEntity findByEmailAddress(String emailAddress) throws EmailNotFoundException {
        CustomerEntity customerEntity = customerRepository.findByEmailAddress(emailAddress);
        if(customerEntity == null){
            throw new EmailNotFoundException();
        }
        return customerEntity;
    }

    @Override
    public Customer registerUser(Customer customer) throws Exception {
        CustomerEntity customerEntity = CustomerConversion.getCustomerEntity(customer);
        customerEntity.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerEntity.setRole(this.getUserRoleEnum(customer.getRole()));
        customerEntity.setAuthorities(UserRole.valueOf(customerEntity.getRole()).getAuthorities());
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

    @Override
    public Customer updateUser(String currentUserName, Customer customer) throws Exception {
        CustomerEntity entity = this.findByUserName(currentUserName);
        CustomerValidation.UpdateValidator(currentUserName,entity,customer,this);
        CustomerEntity newEntity = CustomerConversion.updateEntity(entity,customer);
        newEntity.setRole(this.getUserRoleEnum(customer.getRole()));
        newEntity.setAuthorities(UserRole.valueOf(newEntity.getRole()).getAuthorities());
        return CustomerConversion.getCustomer(customerRepository.save(entity));
    }

    @Override
    public boolean deleteUser(Customer customer) throws Exception {
        CustomerEntity entity = this.findByUserName(customer.getUserName());
        customerRepository.delete(entity);
        return true;
    }

    @Override
    public Customer changeUserStatus(String userName,UserStatusEvents event) throws Exception {
        return CustomerConversion.getCustomer(CustomerStateMachineBuilder.sendMessageToStateMachine
                (this.findByUserName(userName),customerRepository,event,stateMachineFactory));
    }

    @Override
    public Customer updatePassword(String oldPassword, Customer customer) throws Exception {
        CustomerEntity entity = this.findByUserName(customer.getUserName());
        if(!passwordEncoder.matches(oldPassword,entity.getPassword())){
            throw new BadCredentialsException("Password Incorrect");
        }
        entity.setPassword(passwordEncoder.encode(customer.getPassword()));
        return CustomerConversion.getCustomer(customerRepository.save(entity));
    }

    @Override
    public Customer resetPassword(Customer customer) throws Exception {
        CustomerEntity entity = this.findByEmailAddress(customer.getEmailAddress());
        if(!entity.getUserName().equals(customer.getUserName())) {
            throw new NoResultException("You are not allowed");
        }
        String password = randomPassword();
        entity.setPassword(passwordEncoder.encode(password));
        customer = CustomerConversion.getCustomer(customerRepository.save(entity));
        emailService.sendNewPasswordEmail(customer.getFirstName()+" "+customer.getLastName(),password,customer.getEmailAddress());
        return customer;
    }

    @Override
    public List<Customer> getAllUsers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        for (CustomerEntity entity: customerEntities) {
            customers.add(CustomerConversion.getCustomer(entity));
        }
        return customers;
    }

    private String randomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:,<.>/?";
        return RandomStringUtils.random( 10, characters );
    }

    private void authenticate(String userName, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
    }

    private String getUserRoleEnum(String role){
        return UserRole.valueOf(role.toUpperCase()).name();
    }

    //todo: Stop normal users to create role operator and admin

}
