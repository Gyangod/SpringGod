package com.gyangod.service;

import com.gyangod.entity.CustomerEntity;
import com.gyangod.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        CustomerEntity entity = customerRepository.findByUserName(s);

        if(entity == null){
            throw new UsernameNotFoundException("User Not Found");
        }
//        List<SimpleGrantedAuthority> authorities = Arrays.asList(entity.getAuthorities());
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
        return new User(entity.getUserName(), entity.getPassword(), authorities);
    }


}
