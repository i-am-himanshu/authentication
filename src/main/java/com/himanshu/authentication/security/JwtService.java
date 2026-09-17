package com.himanshu.authentication.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtService {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            "my-super-secret-key-that-is-at-least-32-characters-long".getBytes(StandardCharsets.UTF_8)
    );

    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey)
                .compact();
    }
}
