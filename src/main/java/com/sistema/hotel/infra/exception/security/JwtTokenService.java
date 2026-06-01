package com.sistema.hotel.infra.exception.security;

import com.sistema.hotel.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtTokenService implements TokenService {
    private final String issuer;
    private final long expirationMinutes;
    private final SecretKey key;

    public JwtTokenService(
            @Value("${api.security.jwt.issuer:hotel-api}") String issuer,
            @Value("${api.security.jwt.expiration-minutes:60}") long expirationMinutes,
            @Value("${api.security.jwt.secret}") String secret
    ) {
        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException("api.security.jwt.secret must be at least 32 characters");
        }
        this.issuer = issuer;
        this.expirationMinutes = expirationMinutes;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant exp = now.plus(expirationMinutes, ChronoUnit.MINUTES);

        return Jwts.builder()
                .issuer(issuer)
                .subject(user.getLogin())
                .claim("role", user.getRole() != null ? user.getRole().name() : null)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .signWith(key)
                .compact();
    }

    @Override
    public String validateAndGetSubject(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .requireIssuer(issuer)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}

