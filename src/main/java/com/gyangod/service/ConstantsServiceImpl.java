package com.gyangod.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyangod.entity.StandardsEntity;
import com.gyangod.entity.SubjectsEntity;
import com.gyangod.repository.StandardsRepository;
import com.gyangod.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ConstantsServiceImpl implements ConstantsService {

    @Autowired
    private StandardsRepository standardsRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Override
    public List<StandardsEntity> STANDARDS_ENTITY_LIST() throws Exception {
        return standardsRepository.findAll();
    }

    @Override
    public List<String> SUBJECTS_ENTITY_LIST() throws Exception {
        List<String> subjects = subjectsRepository.findAllNames();
        List<String> returnStrings = new LinkedList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (String subject : subjects) {
            SubjectsEntity e = mapper.readValue(subject, SubjectsEntity.class);
            returnStrings.add(e.getName());
        }
        return returnStrings;
    }

    @Override
    public List<String> SUBJECTS_TOPIC_LIST() throws Exception {
        List<SubjectsEntity> subjects = subjectsRepository.findAll();
        List<String> returnStrings = new LinkedList<>();
        for (SubjectsEntity subject : subjects) {
            returnStrings.add(subject.getName());
            if(subject.getTopics() != null && subject.getTopics().size() > 0){
                returnStrings.addAll(subject.getTopics());
            }
        }
        return returnStrings;
    }
}
