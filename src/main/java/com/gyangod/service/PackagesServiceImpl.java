package com.gyangod.service;

import com.gyangod.embeddedentity.PackageOccurrences;
import com.gyangod.entity.CustomerEntity;
import com.gyangod.entity.PackagesEntity;
import com.gyangod.enums.statemachine.PackageState;
import com.gyangod.exception.domain.BlankFieldException;
import com.gyangod.model.Pack;
import com.gyangod.model.PackageSearch;
import com.gyangod.repository.CustomerRepository;
import com.gyangod.repository.PackagesRepository;
import com.gyangod.utils.PackagesConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.gyangod.enums.UserRole.ROLE_STUDENT;
import static com.gyangod.enums.statemachine.PackageState.*;

@Service
public class PackagesServiceImpl implements PackagesService {

    @Autowired
    private PackagesRepository packagesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PackagesConversion packagesConversion;

    @Override
    public Pack savePackage(Pack pack,String userName) throws Exception {
        try{
            CustomerEntity customerEntity = customerRepository.findByUserName(userName);
            PackagesEntity packagesEntity = packagesConversion.getPackagesEntity(pack);
            packagesEntity.setCreatedByUserId(customerEntity.getCustomerId());
            fillUpPackageAmountDetails(packagesEntity);
            packagesEntity.setPackageStatus(getPackageStatusFromRole(customerEntity.getRole()));
            PackagesEntity newPackagesEntity = packagesRepository.save(packagesEntity);
            return packagesConversion.getPackages(newPackagesEntity,customerRepository);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Pack teachPackage(Pack pack, String userName) throws Exception {
        CustomerEntity customerEntity = customerRepository.findByUserName(userName);
        PackagesEntity packagesEntity = packagesConversion.getPackagesEntity(pack);
        packagesEntity.setCreatedByUserId(customerEntity.getCustomerId());
        packagesEntity.setTeacherName(customerEntity.getFirstName()+" "+customerEntity.getLastName());
        packagesEntity.setTeacherId(customerEntity.getCustomerId());
        fillUpPackageAmountDetails(packagesEntity);
        packagesEntity.setPackageStatus(ACTIVE);
        PackagesEntity newPackagesEntity = packagesRepository.save(packagesEntity);
        return packagesConversion.getPackages(newPackagesEntity,customerRepository);
    }

    @Override
    public List<PackagesEntity> getAllPackageNearMe(PackageSearch packageSearch) throws Exception {
        try{
            String[] searchedList = packageSearch.getSearchText().split(" ");
            Point point  = new Point(packageSearch.getLocation());
            Metric metric = (packageSearch.getMetrics()!=null &&
                    packageSearch.getMetrics().equalsIgnoreCase("miles")) ? Metrics.MILES : Metrics.KILOMETERS;
            Distance searchingDistance = new Distance(packageSearch.getDistance(),metric);
            return packagesRepository.findAllByLocationNear(point,searchingDistance);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Pack> getAllPackages() throws Exception {
        List<Pack> packs = null;
        Iterable<PackagesEntity> entities = packagesRepository.findAll();
        for (PackagesEntity entity: entities) {
            if(packs == null){
                packs = new ArrayList<>();
            }
            packs.add(packagesConversion.getPackages(entity,customerRepository));
        }
        return packs;
    }

    private PackageState getPackageStatusFromRole(String userRole) {
        if(userRole.equalsIgnoreCase(ROLE_STUDENT.name())) {
            return STUDENT;
        }
        else {
            return INACTIVE;
        }
    }
    private void fillUpPackageAmountDetails(PackagesEntity packagesEntity) throws BlankFieldException, ParseException {
        if(packagesEntity.getFixedCourse() && (packagesEntity.getMapOccurrences() == null || packagesEntity.getMapOccurrences().size() == 0)){
            throw new BlankFieldException("Batch");
        }
        if(packagesEntity.getMapOccurrences() != null && packagesEntity.getMapOccurrences().size() > 0) {
            for (List<PackageOccurrences> packages: packagesEntity.getMapOccurrences().values()) {
                if(packages!=null && packages.size() > 0) {
                    double weekDiff = 0.0;
                    for (PackageOccurrences occurrence : packages) {
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                        Date from = format.parse(occurrence.getFromTime());
                        Date to = format.parse(occurrence.getToTime());
                        double diff = (double) (from.getTime() - to.getTime());
                        occurrence.setTimeDifference(diff);
                        weekDiff += diff;
                    }
                    packagesEntity.setTotalMonthHours(weekDiff * 4);
                    packagesEntity.setTotalWeekHours(weekDiff);
                }
            }
        }
        if(packagesEntity.getTotalWeekHours() <= 0.0 && packagesEntity.getTotalMonthHours() <= 0.0){
            throw new BlankFieldException("Time");
        } else if(packagesEntity.getTotalWeekHours() > 0.0){
            packagesEntity.setTotalMonthHours(packagesEntity.getTotalWeekHours() * 4);
        } else {
            packagesEntity.setTotalWeekHours(packagesEntity.getTotalMonthHours() / 4);
        }
        if(packagesEntity.getCostPerHour() > 0.0) {
            packagesEntity.setWeeklyCost(packagesEntity.getCostPerHour() * packagesEntity.getTotalWeekHours());
            packagesEntity.setMonthlyCost(packagesEntity.getCostPerHour() * packagesEntity.getTotalMonthHours());
        } else if (packagesEntity.getWeeklyCost() > 0.0) {
            packagesEntity.setCostPerHour(packagesEntity.getWeeklyCost() / packagesEntity.getTotalWeekHours());
            packagesEntity.setMonthlyCost(packagesEntity.getWeeklyCost() * 4);
        } else if(packagesEntity.getMonthlyCost() > 0.0) {
            packagesEntity.setWeeklyCost(packagesEntity.getMonthlyCost() / 4);
            packagesEntity.setCostPerHour(packagesEntity.getMonthlyCost() / packagesEntity.getTotalMonthHours());
        } else {
            throw new BlankFieldException("Cost");
        }
    }
}
