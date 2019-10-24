package com.gyangod.repository;

import com.gyangod.entity.GroupsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<GroupsEntity,String> {
}
