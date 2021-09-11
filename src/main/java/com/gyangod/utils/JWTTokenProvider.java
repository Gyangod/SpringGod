package com.gyangod.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gyangod.model.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

/*import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;*/
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.gyangod.constants.SecurityConstant.*;
import static java.util.Arrays.stream;

@Component
public class JWTTokenProvider {
    //todo: TAKE key pair from more secured and consistent location
    /*KeyPairGenerator kpg;
    RSAPublicKey rsaPublicKey = null;
    RSAPrivateKey rsaPrivateKey = null;
    {
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();
            rsaPublicKey = (RSAPublicKey) kp.getPublic();
            rsaPrivateKey = (RSAPrivateKey) kp.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    Algorithm algorithmRS = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);*/

    @Value("${jwt.secret}")
    private String jwtSecret;

    Algorithm algorithm = Algorithm.HMAC512(jwtSecret);

    public String generateJwtToken(UserPrincipal principal){
        String[] claims = getClaimsForUser(principal);
        return JWT.create().withIssuer(GYANGOD_PVT_LTD).withIssuedAt(new Date())
                .withAudience(GYANGOD_ADMINISTRATION)
                .withSubject(principal.getUsername()).withArrayClaim(AUTHORITIES,claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .sign(algorithm);
    }

    private String[] getClaimsForUser(UserPrincipal principal) {
        List<String> authorities = new ArrayList<>();
        for(GrantedAuthority authority: principal.getAuthorities()){
            authorities.add(authority.getAuthority());
        }
        return authorities.toArray(new String[0]);
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        String[] claims = getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private String[] getClaimsFromToken(String token) {
        DecodedJWT jwt = getJwtVerifier(token);
        return jwt.getClaim(AUTHORITIES).asArray(String.class);
    }

    private DecodedJWT getJwtVerifier(String token){
        DecodedJWT jwt;
        try{
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(GYANGOD_PVT_LTD).build();
            jwt  = verifier.verify(token);
        }catch(JWTVerificationException e){
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }

        return jwt;
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authenticationToken;
    }

    public Boolean isTokenValid(String username, String token){
        DecodedJWT jwt = getJwtVerifier(token);
        return StringUtils.isNotEmpty(username) && !isTokenExpired(jwt,token);
    }

    private boolean isTokenExpired(DecodedJWT jwt, String token) {
        Date expirationDate = jwt.getExpiresAt();
        return expirationDate.before(new Date());
    }

    public String getSubject(String token){
        DecodedJWT jwt = getJwtVerifier(token);
        return jwt.getSubject();
    }
}
