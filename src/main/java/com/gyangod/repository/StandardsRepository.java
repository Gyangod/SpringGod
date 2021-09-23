package com.gyangod.repository;

import com.gyangod.entity.StandardsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardsRepository extends MongoRepository<StandardsEntity, String> {
}
