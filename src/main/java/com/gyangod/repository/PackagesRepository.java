package com.gyangod.repository;

import com.gyangod.entity.PackagesEntity;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackagesRepository extends MongoRepository<PackagesEntity,String> {

    List<PackagesEntity> findAllByLocationNear(Point location, Distance distance);

    List<PackagesEntity> findAllByCreatedByUserId(String username);

}
