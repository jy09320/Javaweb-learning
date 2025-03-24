package com.jy;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWT {
    @Test
    public void testGenJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","jy");
        String jwt= Jwts.builder()
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256,"ank=")
                .compact();
        System.out.println(jwt);
    }
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("ank=")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiankiLCJpZCI6MSwiZXhwIjoxNzQyODc1MzAyfQ.w1fMYy8_g1BZp-uDYE6yduWeHR6GrtEp1KrpJfFKr7o")
                .getBody();
        System.out.println(claims);
    }
}
