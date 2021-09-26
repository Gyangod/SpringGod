package com.gyangod.service;

import com.gyangod.entity.StandardsEntity;

import java.util.List;

public interface ConstantsService {

    //todo: Cache this service

    List<StandardsEntity> STANDARDS_ENTITY_LIST() throws Exception;

    List<String> SUBJECTS_ENTITY_LIST() throws Exception;

    List<String> SUBJECTS_TOPIC_LIST() throws Exception;
}
