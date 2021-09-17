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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.gyangod.constants.FileConstant.FORWARD_SLASH;
import static com.gyangod.constants.FileConstant.USER_FOLDER;
import static com.gyangod.constants.SecurityConstant.AUTHORIZATION_HEADER;
import static com.gyangod.constants.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Controller
@RequestMapping(value = {"/","/api/user"})
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

    @PostMapping(value = "/login")
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

    @PostMapping(value = "/update/{userName}")
    public ResponseEntity<Customer> updateUser(@PathVariable(value = "userName") String userName,@RequestBody Customer customer, @RequestHeader(AUTHORIZATION_HEADER) String jwtToken) throws Exception {
        CustomerValidation.ValidateCustomer(customer);
        customer.setUserName(changeUserNameToLowerCase(customer.getUserName()));
        Customer newCustomer = customerService.updateUser(userName,customer,jwtToken.split(" ")[1]);
        HttpHeaders headers = getHttpHeaders(newCustomer);
        return new ResponseEntity<>(newCustomer,headers,HttpStatus.OK);
    }

    @PostMapping(value = "/save/picture/{userName}")
    public ResponseEntity<Customer> saveUserPicture(@PathVariable(value = "userName") String userName,@RequestParam(value = "picture") MultipartFile file,
                                                    @RequestHeader(AUTHORIZATION_HEADER) String jwtToken) throws Exception {
        return new ResponseEntity<>(customerService.updatePicture(userName,file,jwtToken.split(" ")[1]),HttpStatus.OK);
    }

    @PostMapping(path = "/change/password")
    public ResponseEntity<Customer> updatePassword(@RequestParam(value = "password") String password,@RequestBody Customer customer,
                                                   @RequestHeader(AUTHORIZATION_HEADER) String jwtToken) throws Exception {
        CustomerValidation.ValidateCustomer(customer);
        customer.setUserName(changeUserNameToLowerCase(customer.getUserName()));
        CustomerValidation.validatePassword(customer.getPassword());
        return new ResponseEntity<>(customerService.updatePassword(password,customer,jwtToken.split(" ")[1]),HttpStatus.OK);
    }

    @GetMapping(value = "/change/status/{userName}/and/{event}")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public ResponseEntity<Customer> changeUserStatus(@PathVariable(value = "event") String event,@PathVariable(value = "userName") String userName) throws Exception {
        UserStatusEvents statusEvents = UserStatusEvents.valueOf(event);
        return new ResponseEntity<>(customerService.changeUserStatus(userName,statusEvents),HttpStatus.OK);
    }

    @GetMapping(path = "/get/all")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public ResponseEntity<List<Customer>> getAllUsers( @RequestParam(value = "page") Integer pageNo, @RequestParam(value = "size") Integer pageSize, @RequestParam(value="sort",required = false) String sortBy,
                                                       @RequestParam(value = "asc",defaultValue = "true") boolean ascending) throws Exception {
        return new ResponseEntity<>(customerService.getAllUsers(pageNo,pageSize,sortBy,ascending),HttpStatus.OK);
    }

    @GetMapping(path = "/image/{username}/{fileName}", produces = {IMAGE_JPEG_VALUE,IMAGE_PNG_VALUE})
    public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + fileName));
    }

    @GetMapping(path = "/image/profile/{username}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable("username") String username) throws Exception {
        return customerService.getTempFiles(username);
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
