package com.fernando.fernando_ecommerce_api.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExists;
import com.fernando.fernando_ecommerce_api.models.Admin;
import com.fernando.fernando_ecommerce_api.repositories.AdminRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin saveAdmin(Admin admin) {
        Optional<Admin> adminByEmail = adminRepository.findByEmail(admin.getEmail());
        if (adminByEmail.isPresent()) {
            throw new EntityAlreadyExists("Admin %s email is already exists".formatted(admin.getEmail()));
        }
        String passwordEncoded = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(passwordEncoded);
        adminRepository.save(admin);
        return admin;
    }

    public Admin updatePassword(Integer id, String newPassword) {
        Admin admin = findById(id);
        String passwordEncoded = passwordEncoder.encode(newPassword);
        adminRepository.updatePassword(id, passwordEncoded);
        admin.setPassword(passwordEncoded);
        return admin;
    }

    public Admin findById(Integer id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isEmpty()) {
            throw new EntityNotFoundException("Admin is not exists");
        }
        return adminOptional.get();
    }
}