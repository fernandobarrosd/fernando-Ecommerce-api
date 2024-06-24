package com.fernando.fernando_ecommerce_api.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fernando.fernando_ecommerce_api.exceptions.InvalidJWTTokenException;

@Service
public class JwtService {
    @Value("${jwt.secret.key}")
    private String jwtSecret;

    public String generateToken(String userEmail, String userRole) {
        return JWT.create()
        .withClaim("userRole", userRole)
        .withClaim("userEmail", userEmail)
        .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String getEmail(String token) {
        try {
            return JWT.decode(token)
            .getClaim("userEmail")
            .asString();
        }
        catch (Exception e) {
            throw new InvalidJWTTokenException("The %s token is invalid".formatted(token));
        }
    }
}