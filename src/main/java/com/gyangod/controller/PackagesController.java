package com.gyangod.controller;

import com.gyangod.enums.PackageState;
import com.gyangod.model.Pack;
import com.gyangod.model.PackageSearch;
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

    @PostMapping(value = "/{userName}/save/{role}")
    public ResponseEntity savePackageFromUser(@PathVariable(value = "userName") String userName,
                                              @PathVariable(value = "role")String role,
                                              @RequestBody Pack pack){
        try{
            PackageState state;
            try{
                 state = PackageState.valueOf(role.toUpperCase());
            }catch (Exception e){
                return new ResponseEntity("Please save as \"student\" or \"teacher\" only",HttpStatus.UNPROCESSABLE_ENTITY);
            }
            return new ResponseEntity(packagesService.savePackage(pack,userName,state),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/search")
    public ResponseEntity getAllPackagesNearMe(@RequestBody PackageSearch search){
        try{
            return new ResponseEntity(packagesService.getAllPackageNearMe(search),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/test")
    public ResponseEntity backendToFrontEnd(@RequestBody Pack pack){
        try{
            //todo:validation of model class
            pack.setPackageStatus(PackageState.ACTIVE.name());
            return new ResponseEntity(packagesService.testEndToEnd(pack),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/test")
    public ResponseEntity backendToFrontEnd(){
        try{
            //todo:validation of model class
            return new ResponseEntity(packagesService.getAllPackages(),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

}
