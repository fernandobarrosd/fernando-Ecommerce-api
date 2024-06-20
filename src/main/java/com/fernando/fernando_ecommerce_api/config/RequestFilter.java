package com.fernando.fernando_ecommerce_api.config;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import com.fernando.fernando_ecommerce_api.services.JwtService;
import com.fernando.fernando_ecommerce_api.models.User;
import com.fernando.fernando_ecommerce_api.repositories.AdminRepository;
import com.fernando.fernando_ecommerce_api.repositories.ClientRepository;


@Component
public class RequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String authorizationHeader = request.getHeader("Authorization");
                if (authorizationHeader != null) {
                    String token = getToken(authorizationHeader);
                    if (token != null) {
                        var email = jwtService.getEmail(token);
                        User user = null;
                        
                    
                        var adminOptional = adminRepository.findByEmail(email);
                        
                        if (adminOptional.isPresent()) {
                            user = adminOptional.get();
                        }
                        else {
                            var clientOptional = clientRepository.findByEmail(email);
                            if (clientOptional.isPresent()) {
                                user = clientOptional.get();
                            }
                        }
                   
                        var authentication = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                        );
                        
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
                    }
                   
                }     
            filterChain.doFilter(request, response);
        
    }

    private String getToken(String authorizationHeader) {
        if (!authorizationHeader.startsWith("Bearer")) {
            return null;
        }
        return authorizationHeader.replace("Bearer ", "");
    }
}