package com.gyangod.repository;

import com.gyangod.entity.SubjectsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectsRepository extends MongoRepository<SubjectsEntity, String> {
    SubjectsEntity findByName(String name);

    @Query(value="{}",fields = "{_id:0,name:1}")
    List<String> findAllNames();
}
