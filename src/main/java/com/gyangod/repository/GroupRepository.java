package com.gyangod.repository;

import com.gyangod.entity.GroupsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<GroupsEntity,String> {
}
