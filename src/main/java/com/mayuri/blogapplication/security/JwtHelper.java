package com.mayuri.blogapplication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtHelper {
	
	//token valid time
    public static final long JWT_TOKEN_VALIDITY = 3 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secret;

    //retrive username from jwt token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    //get expiration date from token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }


    public<T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver ){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //retriving any information from token as well need secrete key
    private Claims  getAllClaimsFromToken(String token){
       return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
       return  expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512 , secret)
                .compact();
    }

    //validate token
    public boolean validateToken(String token , UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
