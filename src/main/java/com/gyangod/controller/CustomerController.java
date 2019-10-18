package com.gyangod.controller;

import com.gyangod.model.Customer;
import com.gyangod.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/user")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/test")
    public ResponseEntity testEndToEnd(@RequestBody Customer customer){
        try{
            //todo:validation of model class
            return new ResponseEntity(customerService.endToEndTest(customer),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/test")
    public ResponseEntity testEndToEnd(){
        try{
            //todo:validation of model class
            return new ResponseEntity("hello",HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
