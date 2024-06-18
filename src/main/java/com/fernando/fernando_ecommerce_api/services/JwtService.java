package com.fernando.fernando_ecommerce_api.services;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fernando.fernando_ecommerce_api.exceptions.InvalidJWTTokenException;

@Service
public class JwtService {
    public String generateToken(String userEmail, String userRole) {
        return JWT.create()
        .withClaim("userRole", userRole)
        .withClaim("userEmail", userEmail)
        .sign(Algorithm.HMAC256("secret"));
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