package com.gyangod.service;

import com.gyangod.embeddedentity.PackageOccurrences;
import com.gyangod.entity.CustomerEntity;
import com.gyangod.entity.PackagesEntity;
import com.gyangod.enums.PackageState;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class PackagesServiceImpl implements PackagesService {

    @Autowired
    private PackagesRepository packagesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Pack testEndToEnd(Pack packages) throws Exception {
        try {
            PackagesEntity entity = PackagesConversion.getPackagesEntity(packages);
            entity = packagesRepository.save(entity);
            return PackagesConversion.getPackages(entity);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Pack savePackage(Pack pack,String userName) throws Exception {
        try{
            CustomerEntity customerEntity = customerRepository.findByUserName(userName);
            PackagesEntity packagesEntity = PackagesConversion.getPackagesEntity(pack);
            packagesEntity.setCreatedByUserId(customerEntity.getCustomerId());
            Integer occurrences = packagesEntity.getOccurrencesList().size();
            packagesEntity.setOccurrences(occurrences);
            Double totalSumOfHours = 0.0;
            Integer sequence = 1;
            if(packagesEntity.getOccurrencesList()!=null) for (PackageOccurrences occurrence: packagesEntity.getOccurrencesList()) {
                Double difference = Double.valueOf(((occurrence.getToTime().getTime() - occurrence.getFromTime().getTime())
                        /(60*60*1000)));
                occurrence.setWorkingHours(difference);
                totalSumOfHours += difference;
                occurrence.setSequence(sequence++);
            } else{
                throw  new Exception("Please give at least one occurrence of your package");
            }
            packagesEntity.setTotalWorkingHours(totalSumOfHours);
            packagesEntity.setPackageStatus(PackageState.ACTIVE);
            return PackagesConversion.getPackages(packagesRepository.save(packagesEntity));
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
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
        try{
            List<Pack> packs = null;
            Iterable<PackagesEntity> entities = packagesRepository.findAll();
            for (PackagesEntity entity: entities) {
                if(packs == null){
                    packs = new ArrayList<>();
                }
                packs.add(PackagesConversion.getPackages(entity));
            }
            return packs;
        }catch (Exception e){
            throw e;
        }
    }
}
