package com.fernando.fernando_ecommerce_api.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fernando.fernando_ecommerce_api.exceptions.EntityAlreadyExists;
import com.fernando.fernando_ecommerce_api.exceptions.EntityNotFoundException;
import com.fernando.fernando_ecommerce_api.exceptions.EqualsPasswordsException;
import com.fernando.fernando_ecommerce_api.models.Admin;
import com.fernando.fernando_ecommerce_api.repositories.AdminRepository;
import com.fernando.fernando_ecommerce_api.requests.CreateAdminRequest;
import com.fernando.fernando_ecommerce_api.responses.AdminResponse;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminResponse saveAdmin(CreateAdminRequest createAdminRequest) {
        if (adminRepository.findByEmail(createAdminRequest.getEmail()).isPresent()) {
            throw new EntityAlreadyExists("Admin %s email is already exists".formatted(createAdminRequest.getEmail()));
        }
        String passwordEncoded = passwordEncoder.encode(createAdminRequest.getPassword());
        createAdminRequest.setPassword(passwordEncoded);
        Admin admin = new Admin(createAdminRequest);
        adminRepository.save(admin);
        return new AdminResponse(admin);
    }

    @Transactional
    public void updatePassword(Integer id, String newPassword) {
        Admin admin = findById(id);
        String currentEncodedPassword = admin.getPassword();
        
        if (passwordEncoder.matches(newPassword, currentEncodedPassword)) {
            throw new EqualsPasswordsException("This %s password is already registered".formatted(newPassword));
        }
        String passwordEncoded = passwordEncoder.encode(newPassword);
        adminRepository.updatePassword(id, passwordEncoded);
    }

    public Admin findById(Integer id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isEmpty()) {
            throw new EntityNotFoundException("Admin is not exists");
        }
        return adminOptional.get();
    }
}