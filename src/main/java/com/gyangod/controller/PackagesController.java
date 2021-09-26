package com.gyangod.controller;

import com.gyangod.entity.StandardsEntity;
import com.gyangod.exception.controller.PackageExceptionHandling;
import com.gyangod.model.Pack;
import com.gyangod.service.ConstantsService;
import com.gyangod.service.PackagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping(value = "/api/pack/")
public class PackagesController extends PackageExceptionHandling {

    @Autowired
    private PackagesService packagesService;

    @Autowired
    private ConstantsService constantsService;

    @PostMapping(value = "teach/{userName}")
    @PreAuthorize("hasAnyAuthority('pack:create')")
    public ResponseEntity<Pack> savePackageFromTeacher(@PathVariable(value = "userName") String userName, @RequestBody Pack pack) throws Exception{
        return new ResponseEntity<>(packagesService.teachPackage(pack,userName),OK);
    }

    @PostMapping(value = "save/{userName}")
    public ResponseEntity<Pack> savePackageFromUser(@PathVariable(value = "userName") String userName, @RequestBody Pack pack) throws Exception{
        return new ResponseEntity<>(packagesService.savePackage(pack,userName), OK);
    }

    @GetMapping(value = "get/subjects")
    public ResponseEntity<List<String>> getAllSubjects() throws Exception {
        return new ResponseEntity<>(constantsService.SUBJECTS_ENTITY_LIST(), OK);
    }

    @GetMapping(value = "get/standards")
    public ResponseEntity<List<StandardsEntity>> getAllStandards() throws Exception {
        return new ResponseEntity<>(constantsService.STANDARDS_ENTITY_LIST(), OK);
    }

    @GetMapping(value = "get/all/subjects")
    public ResponseEntity<List<String>> getAllSubjectAndTopics() throws Exception {
        return new ResponseEntity<>(constantsService.SUBJECTS_TOPIC_LIST(), OK);
    }

    @GetMapping(value = "get/all")
    public ResponseEntity<List<Pack>> getAllPackages() throws Exception {
        return new ResponseEntity<>(packagesService.getAllPackages(), OK);
    }

    /*@PostMapping(value = "search")
    public ResponseEntity getAllPackagesNearMe(@RequestBody PackageSearch search){
        try{
            return new ResponseEntity(packagesService.getAllPackageNearMe(search), OK);
        }catch (Exception e){
            return  new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }*/

}
