package com.fernando.fernando_ecommerce_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExistsException;
import com.fernando.fernando_ecommerce_api.models.Admin;
import com.fernando.fernando_ecommerce_api.repositories.AdminRepository;
import com.fernando.fernando_ecommerce_api.requests.AdminRequest;
import com.fernando.fernando_ecommerce_api.responses.AdminResponse;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminResponse saveAdmin(AdminRequest createAdminRequest) {
        if (adminRepository.findByEmail(createAdminRequest.getEmail()).isPresent()) {
            throw new EntityAlreadyExistsException("Admin %s email is already exists".formatted(createAdminRequest.getEmail()));
        }
        String passwordEncoded = passwordEncoder.encode(createAdminRequest.getPassword());
        createAdminRequest.setPassword(passwordEncoded);
        Admin admin = new Admin(createAdminRequest);
        adminRepository.save(admin);
        return new AdminResponse(admin);
    }
}