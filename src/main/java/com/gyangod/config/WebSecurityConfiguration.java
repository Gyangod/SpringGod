package com.gyangod.config;

import com.gyangod.constants.SecurityConstant;
import com.gyangod.filter.JWTAccessDeniedHandler;
import com.gyangod.filter.JWTAuthenticationEntryPoint;
import com.gyangod.filter.JWTAuthorizationFilter;
import com.gyangod.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JWTAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JWTAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JWTAuthorizationFilter authorizationFilter;

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManagerBean();
    }

   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().cors()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers(SecurityConstant.PUBLIC_URLS).permitAll().anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .and().addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
//               .and().httpBasic();
                /** authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic() ;**/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
