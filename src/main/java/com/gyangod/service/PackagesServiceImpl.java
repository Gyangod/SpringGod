package com.gyangod.service;

import com.gyangod.entity.PackagesEntity;
import com.gyangod.model.Pack;
import com.gyangod.repository.PackagesRepository;
import com.gyangod.utils.PackagesConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackagesServiceImpl implements PackagesService {

    @Autowired
    private PackagesRepository packagesRepository;

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
    public List<Pack> getAllPackages() throws Exception {
        try{
            List<Pack> packs = null;
            Iterable<PackagesEntity> entities = packagesRepository.findAll();
            for (PackagesEntity entity: entities
                 ) {
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
