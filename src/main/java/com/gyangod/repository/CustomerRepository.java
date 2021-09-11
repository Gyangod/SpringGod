package com.gyangod.repository;

import com.gyangod.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

    CustomerEntity findByUserName(String username);
    CustomerEntity findByEmailAddress(String emailAddress);

}
