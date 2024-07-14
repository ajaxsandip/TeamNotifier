package com.team.team.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

// This file is used to make use of jwt dependencies added to create and access jwt tokens

@Service
public class JwtService {
    
    @Value("${spring.security.jwt.secret-key}")
    private String secretKey;

    @Value("${spring.security.jwt.expiration-time}")
    private long jwtExpiration;

    //  extract user name by existing token if already logged in
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    };

    // extract claim by using the arguments token and claim resolver
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    };

    // generate token while logging in
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    };

    // genrate token using exractClaims and UserDetauls
    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails){
        return buildToken(extractClaims, userDetails, jwtExpiration);
    };

    public long getExpirationTime(){
        return jwtExpiration;
    }

    // check if token isValid
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // check if token expired
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // extract expiratuontime from claims
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private String buildToken(
        Map<String, Object> extractClaims,
        UserDetails userDetails,
        long jwtExpiration2
    ){
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration2))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    };

    // if logged in extract all Claims 
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    };

    // get the signinkey from the secretkey 
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    };
}
