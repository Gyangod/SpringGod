package com.gyangod.controller;

import com.gyangod.model.Address;
import com.gyangod.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(value = "/save/{userName}")
    public ResponseEntity saveCustomerAddress(@PathVariable(value = "userName") String userName, @RequestBody Address address){
        try{
            return  new ResponseEntity(addressService.saveAddress(address,userName),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/fetch/all/{userName}")
    public ResponseEntity getAllAddressForCustomer(@PathVariable(value = "userName") String userName){
        try{
            return  new ResponseEntity(addressService.getAllAddressForUser(userName),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
