package com.fernando.fernando_ecommerce_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import com.fernando.fernando_ecommerce_api.requests.LoginRequest;
import com.fernando.fernando_ecommerce_api.responses.LoginResponse;
import com.fernando.fernando_ecommerce_api.exceptions.EntityNotFoundException;
import com.fernando.fernando_ecommerce_api.models.User;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public LoginResponse login(LoginRequest loginRequestBody) {
        var usernamePasswordToken = new UsernamePasswordAuthenticationToken(
            loginRequestBody.email(),
            loginRequestBody.password()
        );
        try  {
            var authentication = authenticationManager.authenticate(usernamePasswordToken);
            var user = (User) authentication.getPrincipal();
            var token = jwtService.generateToken(user.getEmail(), user.getRole().getValue());
            return new LoginResponse(token);
        }
        catch (BadCredentialsException exception) {
            throw new EntityNotFoundException("The user is not exists");
        }
    }
}