package com.gyangod.utils;

import com.gyangod.entity.PackagesEntity;
import com.gyangod.model.Customer;
import com.gyangod.model.Pack;
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

    public PackagesEntity getPackagesEntity(Pack packages){
        PackagesEntity entity = new PackagesEntity();
        entity.setPackageId(packages.getPackageId());
        entity.setStandards(packages.getStandards());
        entity.setName(packages.getName());
        entity.setDescription(packages.getDescription());
        entity.setCourseEndDate(packages.getCourseEndDate());
        if(packages.getCreatedBy() != null){
            entity.setCreatedByUserId(packages.getCreatedBy().getCustomerId());
        }
        if(packages.getTeacher() != null){
            entity.setTeacherId(packages.getTeacher().getCustomerId());
        }
        entity.setTeacherName(packages.getTeacherName());
        entity.setAddOtherMembers(packages.getAddOtherMembers());
        entity.setCostPerHour(packages.getCostPerHour());
        entity.setMonthlyDiscount(packages.getMonthlyDiscount());
        entity.setWeeklyCost(packages.getWeeklyCost());
        entity.setTotalWeekHours(packages.getTotalWeekHours());
        entity.setTotalMonthHours(packages.getTotalMonthHours());
        entity.setMonthlyCost(packages.getMonthlyCost());
        entity.setCourseDuration(packages.getCourseDuration());
        entity.setFixedCourse(packages.getFixedCourse());
        entity.setLocation(packages.getLocation());
        entity.setMapOccurrences(packages.getMapOccurrences());
        entity.setSubjects(packages.getSubjects());
        if(packages.getSubjects() != null) {
            List<String> topics = new ArrayList<>();
            for (String subject : packages.getSubjects()) {
                topics.addAll(subjectsRepository.findByName(subject).getTopics());
            }
            entity.setTopics(topics);
        }
        if(packages.getVisibility() && packages.getActive()){
            entity.setPackageStatus(ACTIVE);
        }
        else if(!packages.getVisibility()){
            entity.setPackageStatus(INVISIBLE);
        }
        else if(!packages.getActive()) {
            entity.setPackageStatus(INACTIVE);
        }
        return entity;
    }

    public Pack getPackages(PackagesEntity packages, CustomerRepository customerRepository){
        Pack entity = getPackWithoutEntity(packages);
        if(StringUtils.isNotEmpty(packages.getCreatedByUserId())){
            if(StringUtils.isNotEmpty(packages.getTeacherId()) && packages.getTeacherId().equals(packages.getCreatedByUserId())){
                Customer c = CustomerConversion.getCustomer(customerRepository.findById(packages.getCreatedByUserId()).orElse(null));
                entity.setTeacher(c);
                entity.setCreatedBy(c);
            } else if (StringUtils.isNotEmpty(packages.getTeacherId())) {
                entity.setCreatedBy(CustomerConversion.getCustomer(
                        customerRepository.findById(packages.getCreatedByUserId()).orElse(null)
                ));
                entity.setTeacher(CustomerConversion.getCustomer(
                        customerRepository.findById(packages.getTeacherId()).orElse(null)
                ));
            } else {
                entity.setCreatedBy(CustomerConversion.getCustomer(
                        customerRepository.findById(packages.getCreatedByUserId()).orElse(null)
                ));
            }

        }
        return entity;
    }

    public Pack getPackWithoutEntity(PackagesEntity packages) {
        Pack entity = new Pack();
        entity.setPackageId(packages.getPackageId());
        entity.setStandards(packages.getStandards());
        entity.setName(packages.getName());
        entity.setDescription(packages.getDescription());
        entity.setCourseEndDate(packages.getCourseEndDate());
        entity.setTeacherName(packages.getTeacherName());
        entity.setAddOtherMembers(packages.getAddOtherMembers());
        entity.setCostPerHour(packages.getCostPerHour());
        entity.setMonthlyDiscount(packages.getMonthlyDiscount());
        entity.setWeeklyCost(packages.getWeeklyCost());
        entity.setTotalWeekHours(packages.getTotalWeekHours());
        entity.setTotalMonthHours(packages.getTotalMonthHours());
        entity.setMonthlyCost(packages.getMonthlyCost());
        entity.setCourseDuration(packages.getCourseDuration());
        entity.setFixedCourse(packages.getFixedCourse());
        entity.setLocation(packages.getLocation());
        entity.setMapOccurrences(packages.getMapOccurrences());
        entity.setSubjects(packages.getSubjects());
        String packageStatus = packages.getPackageStatus().name();
        if (packageStatus.equalsIgnoreCase(ACTIVE.name()) || packageStatus.equalsIgnoreCase(STUDENT.name())) {
            entity.setVisibility(true);
            entity.setActive(true);
        } else if (packageStatus.equalsIgnoreCase(INVISIBLE.name())){
            entity.setVisibility(false);
            entity.setActive(true);
        } else {
            entity.setVisibility(false);
            entity.setActive(false);
        }
        return entity;
    }

}
