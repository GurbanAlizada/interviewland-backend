package com.example.interviewlandbackend.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.interviewlandbackend.exception.TokenNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TokenGenerator {


    @Value("${jwt-variables.KEY}")
    private String KEY;

    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;

    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private Integer EXPIRES_ACCESS_TOKEN_MINUTE;

    @Value("${jwt-variables.EXPIRES_REFRESH_TOKEN_MINUTE}")
    private Integer EXPIRES_REFRESH_TOKEN_MINUTE;



    public Map<String , String > generateToken(Authentication authentication){

        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();


        String access_token = JWT.create()
                .withSubject(username)
                .withIssuer(ISSUER)
                .withClaim("roles" , user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .withExpiresAt(new Date(System.currentTimeMillis() + (EXPIRES_ACCESS_TOKEN_MINUTE * 60 * 1000)))
                .sign(Algorithm.HMAC256(KEY.getBytes()));


        String refresh_token = JWT.create()
                .withSubject(username)
                .withIssuer(ISSUER)
                .withExpiresAt(new Date(System.currentTimeMillis() + (EXPIRES_REFRESH_TOKEN_MINUTE * 60 * 1000)))
                .withClaim("roles" , user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(Algorithm.HMAC256(KEY.getBytes()));


        Map<String, String > tokens = new HashMap<>();
        tokens.put("access_token" , access_token);
        tokens.put("refresh_token" , refresh_token);

        return tokens;
    }


    public DecodedJWT verifyJWT(String token){
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        try {
            return verifier.verify(token);
        }catch (Exception e){
            throw new TokenNotFoundException("could not found this token");
        }

    }







}
