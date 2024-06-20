package com.fernando.fernando_ecommerce_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private OncePerRequestFilter requestFilter;

    @Autowired
    private AuthenticationEntryPoint entryPoint;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(httpRequest -> {
            httpRequest
            .requestMatchers(HttpMethod.POST, "/admin", "/client").permitAll()
            .requestMatchers(HttpMethod.GET, "/products", "/products/*").permitAll()
            .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PATCH, "/products/*/unitPrice/*").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/products/*").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/orders").hasRole("CLIENT")
            .requestMatchers(HttpMethod.GET, "/orders").hasRole("CLIENT")
            .requestMatchers(HttpMethod.DELETE, "/orders/*").hasRole("CLIENT")
            .requestMatchers(HttpMethod.GET, "/orders/*").hasRole("CLIENT")
            .requestMatchers(HttpMethod.GET, "/orders/*/products").hasRole("CLIENT");
        })
        .exceptionHandling(exception -> {
            exception.authenticationEntryPoint(entryPoint);
        })
        .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }
}