package com.gyangod.repository;

import com.gyangod.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity,String> {
    AddressEntity findBy_id(String id);
}
