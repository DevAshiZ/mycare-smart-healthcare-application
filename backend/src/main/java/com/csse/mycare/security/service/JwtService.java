package com.csse.mycare.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        log.trace("Extracted claims: {}", claims);
        return claimsResolver.apply(claims);
    }

    // Generate token without extra claims
    public String generateToken(UserDetails userDetails) {
        log.trace("Generating token for user: {}", userDetails.getUsername());
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken);
        log.trace("Validating token for user: {}", username);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    // Generate token with extra claims
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5)) // 1 hour
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username from token
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    // Extract expiration date from token
    public Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build().parseClaimsJws(jwtToken)
                .getBody();
    }

    // Check if token is expired
    private Key getSignInKey() {
        if (SECRET_KEY == null) {
            log.error("SECRET_KEY is null");
            throw new IllegalArgumentException("SECRET_KEY cannot be null");
        }
        byte[] secretBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    public boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }
}
