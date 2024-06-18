package com.fernando.fernando_ecommerce_api.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.fernando.fernando_ecommerce_api.exceptions.EntityNotFoundException;
import com.fernando.fernando_ecommerce_api.models.Admin;
import com.fernando.fernando_ecommerce_api.models.Client;
import com.fernando.fernando_ecommerce_api.repositories.AdminRepository;
import com.fernando.fernando_ecommerce_api.repositories.ClientRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Client> clientOptional = clientRepository.findByEmail(email);
        if (clientOptional.isPresent()) {
            return clientOptional.get();
        }
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            return adminOptional.get();
        }
        throw new EntityNotFoundException("The user is not exists");
    }
}