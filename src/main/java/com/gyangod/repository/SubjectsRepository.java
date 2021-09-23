package com.gyangod.repository;

import com.gyangod.entity.SubjectsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends MongoRepository<SubjectsEntity, String> {
}
