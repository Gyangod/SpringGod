package com.gyangod.service;

import com.gyangod.entity.CustomerEntity;
import com.gyangod.entity.GroupsEntity;
import com.gyangod.entity.PackagesEntity;
import com.gyangod.model.Group;
import com.gyangod.repository.CustomerRepository;
import com.gyangod.repository.GroupRepository;
import com.gyangod.repository.PackagesRepository;
import com.gyangod.utils.GroupConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PackagesRepository packagesRepository;

    @Autowired
    private GroupRepository groupRepository;


    @Override
    public Group saveGroup(Group group, String userName) throws Exception{
        try{
            CustomerEntity customerEntity = customerRepository.findByUserName(userName);
            Optional<PackagesEntity> packagesEntity = packagesRepository.findById(group.getPackageId());
            PackagesEntity packageEntity;
            packagesEntity.ifPresent((Consumer<PackagesEntity>) (packageEntity = packagesEntity.get()));
            GroupsEntity returnGroupsEntity = fillNewGroupDetails(customerEntity,packageEntity,group);
            return GroupConversion.getGroup(groupRepository.save(returnGroupsEntity));
        }catch (Exception e){
            throw e;
        }
    }

    private GroupsEntity fillNewGroupDetails(CustomerEntity customerEntity, PackagesEntity packagesEntity,Group group) throws Exception {
        GroupsEntity groupsEntity;
        Boolean newFlag = false;
        if(group.getPackageId()!=null){
            groupsEntity = groupRepository.findById(group.getPackageId()).get();
        } else {
            groupsEntity = GroupConversion.getGroupEntity(group);
            newFlag = true;
        }
        if(packagesEntity.getCreatedByUserId().equalsIgnoreCase(customerEntity.getCustomerId())){
            if(newFlag) throw new Exception("You Cannot Create your Own Group");
        }
        if(newFlag){
            groupsEntity = GroupConversion.fillPackageDetails(groupsEntity,packagesEntity);
            groupsEntity= GroupConversion.fillUserDetails(groupsEntity,
                    customerRepository.findById(packagesEntity.getCreatedByUserId()).get(),packagesEntity.getPackageStatus(),newFlag);
            group.setPackageId(packagesEntity.getPackageId());
        }
        groupsEntity = GroupConversion.fillUserDetails(groupsEntity,customerEntity,packagesEntity.getPackageStatus(),false);
        groupsEntity.setGroupLastUpdatedByUserName(customerEntity.getUserName());
        return groupsEntity;
    }
}
