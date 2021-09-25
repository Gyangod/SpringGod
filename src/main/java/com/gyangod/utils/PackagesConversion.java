package com.gyangod.utils;

import com.gyangod.entity.PackagesEntity;
import com.gyangod.model.Pack;
import com.gyangod.repository.AddressRepository;
import com.gyangod.repository.CustomerRepository;
import com.gyangod.repository.SubjectsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.gyangod.enums.statemachine.PackageState.*;

@Component
public class PackagesConversion {

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressConversion addressConversion;

    public PackagesEntity getPackagesEntity(Pack packages){
        PackagesEntity entity = new PackagesEntity();
        entity.setPackageId(packages.getPackageId());
        entity.setStandards(packages.getStandards());
        entity.setName(packages.getName());
        entity.setDescription(entity.getDescription());
        if(packages.getCreatedBy() != null){
            entity.setCreatedByUserId(packages.getCreatedBy().getCustomerId());
        }
        if(packages.getTeacher() != null){
            entity.setTeacherId(packages.getTeacher().getCustomerId());
        }
        entity.setCostPerHour(packages.getCostPerHour());
        entity.setMonthlyDiscount(packages.getMonthlyDiscount());
        entity.setOccurrences(packages.getOccurrences());
        entity.setWeeklyCost(packages.getWeeklyCost());
        entity.setTotalWeekHours(packages.getTotalWeekHours());
        entity.setTotalMonthHours(packages.getTotalMonthHours());
        entity.setMonthlyCost(packages.getMonthlyCost());
        if(packages.getAddressLock() != null){
            entity.setAddressLock(packages.getAddressLock());
        }
        if(packages.getAddress() != null){
            entity.setAddressId(packages.getAddress().getAddressId());
            entity.setAddressPlaceName(packages.getAddressPlaceName());
        }
        entity.setLocation(packages.getLocation());
        entity.setOccurrencesList(packages.getOccurrencesList());
        entity.setSubjects(packages.getSubjects());
        if(packages.getSubjects() != null) {
            List<String> topics = new ArrayList<>();
            for (String subject : packages.getSubjects()) {
                topics.addAll(subjectsRepository.findByName(subject).getTopics());
            }
            entity.setTopics(topics);
        }
        if(!packages.getVisibility()){
            entity.setPackageStatus(INVISIBLE);
        } else if(!packages.getActive()) {
            entity.setPackageStatus(INACTIVE);
        }
        return entity;
    }

    public Pack getPackages(PackagesEntity packages, CustomerRepository customerRepository){
        Pack entity = new Pack();
        entity.setPackageId(packages.getPackageId());
        entity.setStandards(packages.getStandards());
        entity.setName(packages.getName());
        entity.setDescription(entity.getDescription());
        if(StringUtils.isNotEmpty(packages.getCreatedByUserId())){
            entity.setCreatedBy(CustomerConversion.getCustomer(
                    customerRepository.findById(packages.getCreatedByUserId()).orElse(null)
            ));
        }
        if(StringUtils.isNotEmpty(packages.getTeacherId())){
            entity.setTeacher(CustomerConversion.getCustomer(
                    customerRepository.findById(packages.getTeacherId()).orElse(null)
            ));
        }
        entity.setCostPerHour(packages.getCostPerHour());
        entity.setMonthlyDiscount(packages.getMonthlyDiscount());
        entity.setOccurrences(packages.getOccurrences());
        entity.setWeeklyCost(packages.getWeeklyCost());
        entity.setTotalWeekHours(packages.getTotalWeekHours());
        entity.setTotalMonthHours(packages.getTotalMonthHours());
        entity.setMonthlyCost(packages.getMonthlyCost());
        if(packages.getAddressLock() != null){
            entity.setAddressLock(packages.getAddressLock());
        }
        if(StringUtils.isNotEmpty(packages.getAddressId())){
            entity.setAddress(addressConversion.getAddress(
                    addressRepository.findById(packages.getAddressId()).orElse(null)
            ));
            entity.setAddressPlaceName(packages.getAddressPlaceName());
        }
        entity.setLocation(packages.getLocation());
        entity.setOccurrencesList(packages.getOccurrencesList());
        entity.setSubjects(packages.getSubjects());
        String packageStatus = packages.getPackageStatus().name();
        if (packageStatus.equalsIgnoreCase(ACTIVE.name()) || packageStatus.equalsIgnoreCase(STUDENT.name())) {
            entity.setVisibility(true);
            entity.setActive(true);
        } else if (packages.getPackageStatus().equals(INVISIBLE)){
            entity.setVisibility(false);
            entity.setActive(true);
        } else {
            entity.setVisibility(false);
            entity.setActive(false);
        }
        return entity;
    }
}
