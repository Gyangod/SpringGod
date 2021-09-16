package com.gyangod.model;

import com.gyangod.entity.CustomerEntity;
import com.gyangod.enums.statemachine.UserStatusState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class UserPrincipal implements UserDetails {
    private CustomerEntity customer;

    public UserPrincipal(CustomerEntity customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return stream(this.customer.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getUserRole(){
        return this.customer.getRole();
    }

    @Override
    public String getPassword() {
        return this.customer.getPassword();
    }

    @Override
    public String getUsername() {
        return this.customer.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.customer.getUserStatus().equals(UserStatusState.EXPIRED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.customer.getUserStatus().equals(UserStatusState.LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.customer.getUserStatus().equals(UserStatusState.INACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return this.customer.getUserStatus().equals(UserStatusState.ACTIVE);
    }
}
