package com.fundacion_habacuc.sistema_de_gestion.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "EstaEsUnaClaveSecretaParaElJwtDebeSerMuyLarga12345678901234567890";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 horas

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // 🔹 Convierte la clave directamente
    }

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey()) // 🔹 No se necesita `SignatureAlgorithm.HS256`
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder() // 🔹 Usar parserBuilder() en vez de parser()
                .setSigningKey(getSigningKey()) // 🔹 setSigningKey() es correcto aquí
                .build()
                .parseClaimsJws(token) // 🔹 parseClaimsJws() es el correcto
                .getBody();
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public boolean isTokenValid(String token, String email) {
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
