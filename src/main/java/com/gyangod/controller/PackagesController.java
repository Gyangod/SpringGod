package com.gyangod.controller;

import com.gyangod.enums.PackageState;
import com.gyangod.model.Pack;
import com.gyangod.service.PackagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/pack")
public class PackagesController {

    @Autowired
    private PackagesService packagesService;

    @PostMapping(value = "/test")
    public ResponseEntity backendToFronEnd(@RequestBody Pack pack){
        try{
            //todo:validation of model class
            pack.setPackageStatus(PackageState.ACTIVE.name());
            return new ResponseEntity(packagesService.testEndToEnd(pack),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/test")
    public ResponseEntity backendToFronEnd(){
        try{
            //todo:validation of model class
            return new ResponseEntity(packagesService.getAllPackages(),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

}
