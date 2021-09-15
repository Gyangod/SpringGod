package com.gyangod.controller;

import com.gyangod.enums.statemachine.UserStatusEvents;
import com.gyangod.exception.controller.UserExceptionHandling;
import com.gyangod.model.Customer;
import com.gyangod.service.CustomerService;
import com.gyangod.validation.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gyangod.constants.SecurityConstant.JWT_TOKEN_HEADER;

@Controller
@RequestMapping(path = {"/","/api/user"})
public class CustomerController extends UserExceptionHandling {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/register")
    public ResponseEntity<Customer> registerUser(@RequestBody Customer customer) throws Exception {
        CustomerValidation.ValidateCustomer(customer);
        customer.setUserName(changeUserNameToLowerCase(customer.getUserName()));
        CustomerValidation.RegisterValidator(customer,customerService);
        return new ResponseEntity<>(customerService.registerUser(customer),HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Customer> loginUser(@RequestBody Customer customer) throws Exception {
        CustomerValidation.ValidateCustomer(customer);
        customer.setUserName(changeUserNameToLowerCase(customer.getUserName()));
        Customer customer1 = customerService.loginUser(customer);
        HttpHeaders headers = getHttpHeaders(customer1);
        return new ResponseEntity<>(customer1,headers,HttpStatus.OK);
    }

    @PostMapping(path = "/reset/password")
    public ResponseEntity<Customer> resetPassword(@RequestBody Customer customer) throws Exception {
        CustomerValidation.ValidateCustomer(customer);
        customer.setUserName(changeUserNameToLowerCase(customer.getUserName()));
        return new ResponseEntity<>(customerService.resetPassword(customer),HttpStatus.OK);
    }

    @PostMapping(path = "/update/{userName}")
    public ResponseEntity<Customer> updateUser(@PathVariable(value = "userName") String userName,@RequestBody Customer customer) throws Exception {
        CustomerValidation.ValidateCustomer(customer);
        customer.setUserName(changeUserNameToLowerCase(customer.getUserName()));
        return new ResponseEntity<>(customerService.updateUser(userName,customer),HttpStatus.OK);
    }

    @PostMapping(path = "/change/password/{password}")
    public ResponseEntity<Customer> updatePassword(@PathVariable(value = "password") String password,@RequestBody Customer customer) throws Exception {
        CustomerValidation.ValidateCustomer(customer);
        customer.setUserName(changeUserNameToLowerCase(customer.getUserName()));
        return new ResponseEntity<>(customerService.updatePassword(password,customer),HttpStatus.OK);
    }

    @GetMapping(path = "/change/status/{userName}/and/{event}")
    public ResponseEntity<Customer> changeUserStatus(@PathVariable(value = "event") String event,@PathVariable(value = "userName") String userName) throws Exception {
        UserStatusEvents statusEvents = UserStatusEvents.valueOf(event);
        return new ResponseEntity<>(customerService.changeUserStatus(userName,statusEvents),HttpStatus.OK);
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Customer>> getAllUsers() throws Exception {
        return new ResponseEntity<>(customerService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping(path = "/test")
    public ResponseEntity<String> testEndToEnd() {
        return new ResponseEntity<>("hello",HttpStatus.OK);
    }

    private String changeUserNameToLowerCase(String userName) {
        return userName.toLowerCase();
    }

    private HttpHeaders getHttpHeaders(Customer customer1) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, customer1.getJwtToken());
        customer1.setJwtToken(null);
        return headers;
    }
}
