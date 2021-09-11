package com.gyangod.filter;

import com.gyangod.utils.JWTTokenProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.gyangod.constants.SecurityConstant.OPTIONS_HTTP_METHOD;
import static com.gyangod.constants.SecurityConstant.TOKEN_PREFIX;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(httpServletRequest.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)){
            httpServletResponse.setStatus(HttpStatus.OK.value());
        } else {
            String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if(StringUtils.isEmpty(authorizationHeader) || !authorizationHeader.startsWith(TOKEN_PREFIX)){
                filterChain.doFilter(httpServletRequest,httpServletResponse);
                return ;
            }
            String token = authorizationHeader.substring(TOKEN_PREFIX.length());
            String userName = jwtTokenProvider.getSubject(token);
//            if(jwtTokenProvider.isTokenValid(userName,token) && SecurityContextHolder.getContext().getAuthentication()==null){ //can remove Security context holder as Session is not using
            if(jwtTokenProvider.isTokenValid(userName,token)) {
                List<GrantedAuthority> authorities =jwtTokenProvider.getAuthorities(token);
                Authentication authentication = jwtTokenProvider.getAuthentication(userName,authorities,httpServletRequest);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
}
