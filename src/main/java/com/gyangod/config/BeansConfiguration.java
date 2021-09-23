package com.gyangod.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyangod.entity.StandardsEntity;
import com.gyangod.entity.SubjectsEntity;
import com.gyangod.repository.StandardsRepository;
import com.gyangod.repository.SubjectsRepository;
import com.gyangod.utils.AddressConversion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class BeansConfiguration {

    private static final Log LOG = LogFactory.getLog(BeansConfiguration.class);

    @Autowired
    private StandardsRepository standardsRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Bean
    public AddressConversion getAddressConversion(){
        return new AddressConversion();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<StandardsEntity>> standardTypeReference = new TypeReference<>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/standards.json");
            try{
                List<StandardsEntity> entities = mapper.readValue(inputStream,standardTypeReference);
                for (StandardsEntity entity : entities) {
                    standardsRepository.save(entity);
                }
                standardTypeReference = null;
                entities = null;
            }catch(Exception e){
                LOG.error(e);
            }
            TypeReference<List<SubjectsEntity>> subjectTypeReference = new TypeReference<>(){};
            inputStream = TypeReference.class.getResourceAsStream("/json/subjects.json");
            try{
                List<SubjectsEntity> entities = mapper.readValue(inputStream,subjectTypeReference);
                for (SubjectsEntity entity : entities) {
                    subjectsRepository.save(entity);
                }
                subjectTypeReference = null;
                entities = null;
            }catch(Exception e){
                LOG.error(e);
            }
        };
    }

}
