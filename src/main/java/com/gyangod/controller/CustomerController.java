package com.gyangod.controller;

import com.gyangod.model.Customer;
import com.gyangod.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller(value = "/user")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(name = "test")
    public ResponseEntity testEndToEnd(@RequestBody Customer customer){
        try{
            //todo:validation of model class
            return new ResponseEntity(customerService.endToEndTest(customer),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity("Not Appropriate",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(name = "test")
    public ResponseEntity testEndToEnd(){
        try{
            //todo:validation of model class
            return new ResponseEntity("hello",HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity("Not Appropriate",HttpStatus.BAD_REQUEST);
        }
    }
}
