package com.gyangod.config;

import com.gyangod.utils.AddressConversion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    public AddressConversion getAddressConversion(){
        return new AddressConversion();
    }
}
