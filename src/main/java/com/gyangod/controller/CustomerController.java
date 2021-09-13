package com.gyangod.controller;

import com.gyangod.exception.controller.UserExceptionHandling;
import com.gyangod.exception.domain.EmailExistException;
import com.gyangod.model.Customer;
import com.gyangod.service.CustomerService;
import com.gyangod.validation.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.gyangod.constants.SecurityConstant.JWT_TOKEN_HEADER;

@Controller
@RequestMapping(path = {"/","/api/user"})
public class CustomerController extends UserExceptionHandling {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/register")
    public ResponseEntity<Customer> registerUser(@RequestBody Customer customer) throws Exception {
        CustomerValidation.ValidateCustomer(customer);
        changeUserNameToLowerCase(customer);
        CustomerValidation.RegisterValidator(customer,customerService);
        return new ResponseEntity<>(customerService.registerUser(customer),HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Customer> loginUser(@RequestBody Customer customer) throws Exception {
        CustomerValidation.ValidateCustomer(customer);
        changeUserNameToLowerCase(customer);
        Customer customer1 = customerService.loginUser(customer);
        HttpHeaders headers = getHttpHeaders(customer1);
        return new ResponseEntity<>(customer1,headers,HttpStatus.OK);
    }

    @GetMapping(path = "/test")
    public ResponseEntity<String> testEndToEnd() {
        return new ResponseEntity<>("hello",HttpStatus.OK);
    }

    private void changeUserNameToLowerCase(Customer customer) {
        customer.setUserName(customer.getUserName().toLowerCase());
    }

    private HttpHeaders getHttpHeaders(Customer customer1) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, customer1.getJwtToken());
        customer1.setJwtToken(null);
        return headers;
    }
}
