package com.gyangod.repository;

import com.gyangod.entity.PackagesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagesRepository extends CrudRepository<PackagesEntity,String> {
}
